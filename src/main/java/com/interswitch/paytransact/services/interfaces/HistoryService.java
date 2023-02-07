package com.interswitch.paytransact.services.interfaces;

import com.interswitch.paytransact.entities.Account;

public interface HistoryService {

    void logAccountHistory(Account account, String body);

//    List<History> getAccountHistory(AccountDto accountDto);
}
