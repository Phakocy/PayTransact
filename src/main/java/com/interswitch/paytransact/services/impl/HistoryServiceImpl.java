package com.interswitch.paytransact.services.impl;

import com.interswitch.paytransact.daos.interfaces.AccountDao;
import com.interswitch.paytransact.daos.interfaces.HistoryDao;
import com.interswitch.paytransact.dtos.AccountDto;
import com.interswitch.paytransact.entities.Account;
import com.interswitch.paytransact.entities.History;
import com.interswitch.paytransact.entities.User;
import com.interswitch.paytransact.services.interfaces.HistoryService;
import com.interswitch.paytransact.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    private final UserService userService;

    private final HistoryDao historyDao;

    private final AccountDao accountDao;

    @Autowired
    public HistoryServiceImpl(UserService userService, HistoryDao historyDao, AccountDao accountDao) {
        this.userService = userService;
        this.historyDao = historyDao;
        this.accountDao = accountDao;
    }

    @Override
    public void logAccountHistory(Integer account, String body) {
        History history = new History();
        history.setBody(body);
        history.setAccount(account);
        history.setDateCreated(new Date());

        historyDao.create(history);
    }

    @Override
    public List<History> getAccountHistory(AccountDto accountDto) {
        User user = userService.loadUserByEmail(accountDto.getEmail());
        Account account = accountDao.getAccountByUserId(user.getId());

        return historyDao.getHistoryList(account);
    }
}
