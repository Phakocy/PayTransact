package com.interswitch.paytransact.services.interfaces;

public interface HistoryService {

    void logAccountHistory(Integer account, String body);

//    List<History> getAccountHistory(AccountDto accountDto);
}
