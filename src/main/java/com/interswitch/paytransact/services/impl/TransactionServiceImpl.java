package com.interswitch.paytransact.services.impl;

import com.interswitch.paytransact.dtos.PaymentDto;
import com.interswitch.paytransact.entities.Account;
import com.interswitch.paytransact.entities.Transaction;
import com.interswitch.paytransact.entities.enums.TransactionStatus;
import com.interswitch.paytransact.exceptions.MainExceptions;
import com.interswitch.paytransact.exceptions.NotFoundException;
import com.interswitch.paytransact.repos.AccountRepository;
import com.interswitch.paytransact.repos.TransactionRepository;
import com.interswitch.paytransact.services.interfaces.AccountService;
import com.interswitch.paytransact.services.interfaces.HistoryService;
import com.interswitch.paytransact.services.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private TransactionRepository transactionRepository;

    public void processTransaction(PaymentDto paymentDto) {
        Long cardNumber = paymentDto.getCardNumber();
        Long accountNumber = paymentDto.getAccountNumber();
        Double amount = paymentDto.getAmount();
        String narration = paymentDto.getNarration();

        if (cardNumber == null || accountNumber == null || amount == null || narration == null) {
            throw new MainExceptions("card number, account number, amount, and narration fields are required");
        }

        Account senderAccount = accountService.getAccountDetailsByCardNumber(cardNumber);
        Account recipientAccount = accountService.getAccountDetailsByAccountNumber(accountNumber);
        Double senderBalance = senderAccount.getBalance();

        Transaction transaction = new Transaction();
        transaction.setAccount(senderAccount);
        transaction.setBalance(senderBalance);
        transaction.setAmount(amount);
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setNarration(narration);
        transaction.setDateCreated(new Date());

        Transaction transactionResult = transactionRepository.save(transaction);
        Long transactionId = transactionResult.getId();

        if (amount > senderBalance) {
            updateTransactionStatus(transactionId, TransactionStatus.DECLINED);
            throw new MainExceptions("insufficient balance");
        }

        senderAccount.setBalance(senderBalance - amount);
        recipientAccount.setBalance(recipientAccount.getBalance() + amount);
        accountRepository.save(senderAccount);
        accountRepository.save(recipientAccount);
//
        historyService.logAccountHistory(senderAccount, "you sent " + amount + " to " + recipientAccount.getAccountNumber());
        historyService.logAccountHistory(recipientAccount, senderAccount.getAccountNumber() + " just sent you " + amount);

        updateTransactionStatus(transactionId, TransactionStatus.SUCCESS);
    }

    Optional<Transaction> getTransactionByTransactionId(Long transactionId) {
        return Optional.ofNullable(transactionRepository.findTransactionById(transactionId).orElseThrow(() -> new NotFoundException("transaction not found with transaction id")));
    }

    void updateTransactionStatus(Long transactionId, TransactionStatus status) {
        Optional<Transaction> transactionUpdate = getTransactionByTransactionId(transactionId);
        transactionUpdate.ifPresent((Transaction transaction1) -> {
            transaction1.setStatus(status);
            transactionRepository.save(transaction1);
        });
    }
}
