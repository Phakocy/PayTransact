package com.interswitch.paytransact.daos.interfaces;

import com.interswitch.paytransact.entities.Transaction;

import java.util.List;

public interface TransactionDao extends BaseDao {
    Transaction findById(Integer transactionId);

    List<Transaction> findTransactionsByAccountId(Integer accountId);
}
