package com.interswitch.paytransact.daos.impl;

import com.interswitch.paytransact.daos.interfaces.TransactionDao;
import com.interswitch.paytransact.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Configuration
public class TransactionDaoImpl implements TransactionDao {
    private SimpleJdbcCall
            create,
            update,
            findById,
            findTransactionsByAccountId;

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        create = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insert_into_transaction_table");
        update = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("update_into_transaction_table");
        findById = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("find_transaction_by_transaction_id");
        findTransactionsByAccountId = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("find_transactions_by_account_id");
    }

    @Override
    public Integer create(Transaction transaction) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("amount", transaction.getAmount())
                .addValue("account_id", transaction.getAccount())
                .addValue("balance", transaction.getBalance())
                .addValue("narration", transaction.getNarration())
                .addValue("status", transaction.getStatus())
                .addValue("date_created", transaction.getDateCreated());
        return (Integer) this.create.execute(in).get("transaction_id");
    }

    @Override
    public void update(Transaction transaction) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("transaction_id", transaction.getId())
                .addValue("balance", transaction.getBalance())
                .addValue("status", transaction.getBalance());

        this.update.execute(in);
    }

    @Override
    public Transaction findById(Integer transactionId) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("transactionId", transactionId);
        Map<String, Object> out = this.findById.execute(in);
        return null;
    }

    @Override
    public List<Transaction> findTransactionsByAccountId(Integer accountId) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("account_id", accountId);
        return (List<Transaction>) this.findTransactionsByAccountId
                .execute(in)
                .get("#result-set-1");
    }
}
