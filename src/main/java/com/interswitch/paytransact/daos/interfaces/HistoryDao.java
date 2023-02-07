package com.interswitch.paytransact.daos.interfaces;

import com.interswitch.paytransact.entities.Account;
import com.interswitch.paytransact.entities.History;

import java.util.List;

public interface HistoryDao extends BaseDao {
    void create(History history);

    List<History> getHistoryList(Account account);
}
