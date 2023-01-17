package com.interswitch.paytransact.services.impl;

import com.interswitch.paytransact.dtos.AccountDto;
import com.interswitch.paytransact.entities.Account;
import com.interswitch.paytransact.entities.User;
import com.interswitch.paytransact.exceptions.MainExceptions;
import com.interswitch.paytransact.exceptions.NotFoundException;
import com.interswitch.paytransact.repos.AccountRepository;
import com.interswitch.paytransact.repos.UserRepository;
import com.interswitch.paytransact.services.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryServiceImpl historyService;

    @Override
    public Account getAccount(AccountDto accountDto) {
        User userDetails = userRepository.findByEmail(accountDto.getEmail()).get();
        Account account = accountRepository.getAccountById(userDetails.getId());
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
//        get user details from accountDto email
        Optional<User> userDetails = Optional.ofNullable(userRepository.findByEmail(accountDto.getEmail()).orElseThrow(() -> (new NotFoundException("could not find user with this email"))));

//        initialize account with random account number and card number and balance 0
        Random objGenerator = new Random();
        Long userId = userDetails.get().getId();
        if (accountRepository.existsAccountByUser_Id(userId))
            throw new MainExceptions("account already exists for this user");

        Account newAccount = new Account();
        newAccount.setUser(userDetails.get());
        newAccount.setBalance(00.0);
        newAccount.setCardNumber(objGenerator.nextLong(9999999999999L));
        newAccount.setAccountNumber(objGenerator.nextLong(999999999999L));
        newAccount.setDateCreated(new Date());
//        save account with user and account details
        accountRepository.save(newAccount);
        historyService.logAccountHistory(newAccount, "new account created");
    }
}
