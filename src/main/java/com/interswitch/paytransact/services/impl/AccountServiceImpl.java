package com.interswitch.paytransact.services.impl;

import com.interswitch.paytransact.dtos.AccountDto;
import com.interswitch.paytransact.entities.Account;
import com.interswitch.paytransact.entities.User;
import com.interswitch.paytransact.exceptions.MainExceptions;
import com.interswitch.paytransact.exceptions.NotFoundException;
import com.interswitch.paytransact.repos.AccountRepository;
import com.interswitch.paytransact.services.interfaces.AccountService;
import com.interswitch.paytransact.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HistoryServiceImpl historyService;

    @Override
    public Account getAccountByUserEmail(AccountDto accountDto) throws NotFoundException {
        User userDetails = userService.loadUserByEmail(accountDto.getEmail());
        Account account = accountRepository.getAccountByUserId(userDetails.getId());
        if (account == null) throw new NotFoundException("account not created for this user");
        return account;
    }

    @Override
    public Account getAccountDetailsByCardNumber(Long cardNumber) throws MainExceptions, NotFoundException {
        if (cardNumber == null) throw new MainExceptions("card number is required");
        return accountRepository.getAccountByCardNumber(cardNumber).orElseThrow(() -> new NotFoundException("card number is not correct"));
    }

    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) throws MainExceptions, NotFoundException {
        if (accountNumber == null) throw new MainExceptions("account number is required");
        return accountRepository.getAccountByAccountNumber(accountNumber).orElseThrow(() -> new NotFoundException("account number is not correct"));
    }

    @Override
    public void createNewAccount(AccountDto accountDto) throws MainExceptions {
        if (accountRepository.existsAccountByUser_Id(userService.loadUserByEmail(accountDto.getEmail()).getId()))
            throw new MainExceptions("account already exists for this user");

        handleNewAccountCreation(accountDto);
    }

    void handleNewAccountCreation(AccountDto accountDto) {
//        initialize account with random account number and card number and set balance 0
        Random objGenerator = new Random();

        Account newAccount = new Account();
        newAccount.setUser(userService.loadUserByEmail(accountDto.getEmail()));
        newAccount.setBalance(00.0);
        newAccount.setCardNumber(objGenerator.nextLong(9999999999999L));
        newAccount.setAccountNumber(objGenerator.nextLong(999999999999L));
        newAccount.setDateCreated(new Date());

//        save account with user and account details and log account history
        accountRepository.save(newAccount);
        historyService.logAccountHistory(newAccount, "new account created");
    }
}


