package com.interswitch.paytransact.services.interfaces;

import com.interswitch.paytransact.entities.Account;
import com.interswitch.paytransact.entities.History;

import java.util.List;

public interface HistoryService {

    void logAccountHistory(Account account, String body);

    List<History> getAccountHistory();
}
