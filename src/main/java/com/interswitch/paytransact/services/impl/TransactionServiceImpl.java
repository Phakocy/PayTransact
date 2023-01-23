package com.interswitch.paytransact.services.impl;

import com.interswitch.paytransact.dtos.AccountDto;
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
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionServiceImpl implements TransactionService {
    private static final String TOPIC = "Transaction_Topic";
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    @Override
    public void processTransaction(PaymentDto paymentDto) throws MainExceptions {
        Long cardNumber = paymentDto.getCardNumber();
        Long accountNumber = paymentDto.getAccountNumber();
        Double amount = paymentDto.getAmount();
        String narration = paymentDto.getNarration();

        if (cardNumber == null || accountNumber == null || amount == null || narration == null || narration.isEmpty()) {
            throw new MainExceptions("card number, account number, amount, and narration fields are required");
        } else if (amount < 10) {
            throw new MainExceptions("can not transfer amounts less than 10.00");
        }

        Account senderAccount = accountService.getAccountDetailsByCardNumber(cardNumber);
        Account recipientAccount = accountService.getAccountDetailsByAccountNumber(accountNumber);
        Double senderBalance = senderAccount.getBalance();

        Long transactionId = generateTransaction(senderAccount, senderBalance, amount, TransactionStatus.PENDING, narration);

        if (amount > senderBalance) {
            updateTransaction(transactionId, TransactionStatus.DECLINED, senderBalance);
            throw new MainExceptions("insufficient balance");
        }

        senderAccount.setBalance(senderBalance - amount);
        recipientAccount.setBalance(recipientAccount.getBalance() + amount);
        Account senderAccountResult = accountRepository.save(senderAccount);
        accountRepository.save(recipientAccount);

        historyService.logAccountHistory(senderAccount, "you sent " + amount + " to " + recipientAccount.getAccountNumber());
        historyService.logAccountHistory(recipientAccount, senderAccount.getAccountNumber() + " just sent you " + amount);

        updateTransaction(transactionId, TransactionStatus.SUCCESS, senderAccountResult.getBalance());
    }

    Long generateTransaction(Account account, Double balance, Double amount, TransactionStatus status, String narration) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setBalance(balance);
        transaction.setAmount(amount);
        transaction.setStatus(status);
        transaction.setNarration(narration);
        transaction.setDateCreated(new Date());

        Transaction transactionResult = transactionRepository.save(transaction);
        return transactionResult.getId();
    }

    Optional<Transaction> getTransactionByTransactionId(Long transactionId) throws NotFoundException {
        return Optional.ofNullable(transactionRepository.findTransactionById(transactionId).orElseThrow(() -> new NotFoundException("transaction not found with transaction id")));
    }

    void updateTransaction(Long transactionId, TransactionStatus status, Double balance) {
        Optional<Transaction> transactionUpdate = getTransactionByTransactionId(transactionId);
        transactionUpdate.ifPresent((Transaction transaction1) -> {
            transaction1.setStatus(status);
            transaction1.setBalance(balance);
            //        SEND PAYMENT NOTIFICATION TO KAFKA
            kafkaTemplate.send(TOPIC, transaction1);

            transactionRepository.save(transaction1);
        });
    }

    @Override
    public Optional<List<Transaction>> getTransactionListByAccount(AccountDto accountDto) throws NotFoundException {
        Account account = accountService.getAccountByUserEmail(accountDto);
        return Optional.ofNullable(transactionRepository.findTransactionsByAccountId(account.getId()).orElseThrow(() -> new NotFoundException("transaction list not found with this account")));
    }
}
