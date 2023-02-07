package com.interswitch.paytransact.services.interfaces;

import com.interswitch.paytransact.dtos.AccountDto;
import com.interswitch.paytransact.entities.History;

import java.util.List;

public interface HistoryService {

    void logAccountHistory(Integer account, String body);

    List<History> getAccountHistory(AccountDto accountDto);
}
