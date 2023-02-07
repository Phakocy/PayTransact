package com.interswitch.paytransact.daos.impl;

import com.interswitch.paytransact.daos.interfaces.TransactionDao;
import com.interswitch.paytransact.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class TransactionDaoImpl implements TransactionDao {
    private SimpleJdbcCall
            findById,
            findTransactionsByAccountId;

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        findById = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("find_transaction_by_transaction_id");
        findTransactionsByAccountId = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("find_transactions_by_account_id");
    }

    @Override
    public Transaction findById(Integer transactionId) {
        return null;
    }

    @Override
    public List<Transaction> findTransactionsByAccountId(Integer accountId) {
        return null;
    }
}
