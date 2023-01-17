package com.interswitch.paytransact.services.impl;

import com.interswitch.paytransact.dtos.AccountDto;
import com.interswitch.paytransact.entities.Account;
import com.interswitch.paytransact.entities.History;
import com.interswitch.paytransact.entities.User;
import com.interswitch.paytransact.repos.AccountRepository;
import com.interswitch.paytransact.repos.HistoryRepository;
import com.interswitch.paytransact.services.interfaces.HistoryService;
import com.interswitch.paytransact.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void logAccountHistory(Account account, String body) {
        History history = new History();
        history.setBody(body);
        history.setAccount(account);
        history.setDateCreated(new Date());
        historyRepository.save(history);
    }

    @Override
    public List<History> getAccountHistory(AccountDto accountDto) {
        User user = userService.loadUserByEmail(accountDto.getEmail());
        Account account = accountRepository.getAccountByUserId(user.getId());

        return historyRepository.findHistoriesByAccount_Id(account.getId());
    }
}
