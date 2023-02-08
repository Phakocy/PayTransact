package com.interswitch.paytransact.services.impl;

import com.interswitch.paytransact.daos.interfaces.AccountDao;
import com.interswitch.paytransact.daos.interfaces.TransactionDao;
import com.interswitch.paytransact.dtos.AccountDto;
import com.interswitch.paytransact.dtos.PaymentDto;
import com.interswitch.paytransact.entities.Account;
import com.interswitch.paytransact.entities.Transaction;
import com.interswitch.paytransact.entities.enums.TransactionStatus;
import com.interswitch.paytransact.exceptions.MainExceptions;
import com.interswitch.paytransact.exceptions.NotFoundException;
import com.interswitch.paytransact.services.interfaces.AccountService;
import com.interswitch.paytransact.services.interfaces.HistoryService;
import com.interswitch.paytransact.services.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {
    private static final String TOPIC = "Transaction_Topic";


    private final AccountDao accountDao;
    private final TransactionDao transactionDao;
    private final AccountService accountService;
    private final HistoryService historyService;

    private KafkaTemplate<String, Transaction> kafkaTemplate;

    @Autowired
    public TransactionServiceImpl(AccountDao accountDao, TransactionDao transactionDao, AccountService accountService, HistoryService historyService, KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.accountDao = accountDao;
        this.transactionDao = transactionDao;
        this.accountService = accountService;
        this.historyService = historyService;
        this.kafkaTemplate = kafkaTemplate;
    }

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

        Account senderAccount = accountService
                .getAccountDetailsByCardNumber(cardNumber);
        Account recipientAccount = accountService
                .getAccountDetailsByAccountNumber(accountNumber);
        Double senderBalance = senderAccount.getBalance();

        Integer transactionId = generateTransaction(senderAccount.getId(), senderBalance, amount, narration);

        if (amount > senderBalance) {
            updateTransaction(transactionId, TransactionStatus.DECLINED, senderBalance);
            throw new MainExceptions("insufficient balance");
        }

        senderAccount.setBalance(senderBalance - amount);
        recipientAccount.setBalance(recipientAccount.getBalance() + amount);

        accountDao.update(senderAccount);
        accountDao.update(recipientAccount);

        Account account = accountDao.getAccountById(senderAccount.getId());

        historyService.logAccountHistory(senderAccount.getId(), "you sent " + amount + " to " + recipientAccount.getAccountNumber());
        historyService.logAccountHistory(recipientAccount.getId(), senderAccount.getAccountNumber() + " just sent you " + amount);

        updateTransaction(transactionId, TransactionStatus.SUCCESS, account.getBalance());
    }

    Integer generateTransaction(Integer accountId, Double balance, Double amount, String narration) {
        Transaction transaction = new Transaction();
        transaction.setAccount(accountId);
        transaction.setBalance(balance);
        transaction.setAmount(amount);
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setNarration(narration);
        transaction.setDateCreated(new Date());

        return transactionDao.create(transaction);
    }

    Transaction getTransactionByTransactionId(Integer transactionId) throws NotFoundException {
        Transaction transaction = transactionDao.findById(transactionId);
        if (transaction == null) throw new NotFoundException("transaction not found with transaction id");
        return transaction;
    }

    void updateTransaction(Integer transactionId, TransactionStatus status, Double balance) {
        Transaction transaction = getTransactionByTransactionId(transactionId);
        transaction.setStatus(status);
        transaction.setBalance(balance);

        //        SEND PAYMENT NOTIFICATION TO KAFKA
        kafkaTemplate.send(TOPIC, transaction);

        transactionDao.update(transaction);
    }

    @Override
    public List<Transaction> getTransactionListByAccount(AccountDto accountDto) throws NotFoundException {
        Account account = accountService.getAccountByUserEmail(accountDto);
        return transactionDao.findTransactionsByAccountId(account.getId());
    }
}
