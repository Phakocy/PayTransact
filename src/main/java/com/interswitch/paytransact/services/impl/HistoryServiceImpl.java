package com.interswitch.paytransact.services.impl;

import com.interswitch.paytransact.daos.interfaces.HistoryDao;
import com.interswitch.paytransact.entities.History;
import com.interswitch.paytransact.services.interfaces.HistoryService;
import com.interswitch.paytransact.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private UserService userService;

    private HistoryDao historyDao;

    @Override
    public void logAccountHistory(Integer account, String body) {
        History history = new History();
        history.setBody(body);
        history.setAccount(account);
        history.setDateCreated(new Date());

        historyDao.create(history);

//        historyRepository.save(history);
    }

//    @Override
//    public List<History> getAccountHistory(AccountDto accountDto) {
//        User user = userService.loadUserByEmail(accountDto.getEmail());
////        Account account = accountRepository.getAccountByUserId(user.getId());
//
////        return historyRepository.findHistoriesByAccount_Id(account.getId());
//    }
}
