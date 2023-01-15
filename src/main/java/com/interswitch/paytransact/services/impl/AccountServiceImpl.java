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

    @Override
    public Account getAccount(AccountDto accountDto) {
        User userDetails = userRepository.findByEmail(accountDto.getEmail()).get();
        Account account = accountRepository.getAccountById(userDetails.getId());
        if (account == null) throw new NotFoundException("account not created for this user");
        return account;
    }

    @Override
    public void createNewAccount(AccountDto accountDto) throws MainExceptions {
//        get user details from accountDto email
        Optional<User> userDetails = userRepository.findByEmail(accountDto.getEmail());

//        initialize account with random account number and balance 0
        Random objGenerator = new Random();
        Long userId = userDetails.get().getId();
        if (accountRepository.existsAccountByUser_Id(userId)) {
            throw new MainExceptions("account already exists for this user");
        }
        Account newProject = new Account();
        newProject.setUser(userDetails.get());
        newProject.setBalance(00.0);
        newProject.setCardNumber(objGenerator.nextLong(9999999999999L));
        newProject.setDateCreated(new Date());
//        save account with user and account details
        accountRepository.save(newProject);
    }
}
