package com.interswitch.paytransact.daos.impl;

import com.interswitch.paytransact.daos.interfaces.AccountDao;
import com.interswitch.paytransact.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.util.Date;
import java.util.Map;

@Configuration
public class AccountDaoImpl implements AccountDao {
    private SimpleJdbcCall
            create,
            update,
            findById,
            findByUserId,
            findByCardNumber,
            findByAccountNumber;

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        create = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insert_into_account_table");
        update = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("update_into_account_table");
        findById = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("find_account_by_account_id");
        findByUserId = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("find_account_by_user_id");
        findByCardNumber = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("find_account_by_card_number");
        findByAccountNumber = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("find_account_by_account_number");
    }

    @Override
    public Integer create(Account account) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("userId", account.getUser())
                .addValue("balance", account.getBalance())
                .addValue("cardNumber", account.getCardNumber())
                .addValue("accountNumber", account.getAccountNumber())
                .addValue("dateCreated", account.getDateCreated());

        return (Integer) this.create.execute(in).get("account_id");
    }

    @Override
    public void update(Account account) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("accountId", account.getId())
                .addValue("balance", account.getBalance());

        this.update.execute(in).get("account_id");
    }

    @Override
    public Account getAccountById(Integer accountId) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("accountId", accountId);
        return setAccountObject(this.findById.execute(in));
    }

    @Override
    public Account getAccountByUserId(Integer userId) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("userid", userId);
        return setAccountObject(this.findByUserId.execute(in));
    }

    @Override
    public Account getAccountByAccountNumber(Long accountNumber) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("account_number", accountNumber);
        return setAccountObject(this.findByAccountNumber.execute(in));
    }

    @Override
    public Account getAccountByCardNumber(Long cardNumber) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("card_number", cardNumber);
        return setAccountObject(this.findByCardNumber.execute(in));
    }

    @Override
    public boolean existsAccountByUserId(Integer userid) {
        Account account = getAccountByUserId(userid);
        return account.getAccountNumber() != null
                && account.getCardNumber() != null;
    }

    private Account setAccountObject(Map<String, Object> out) {
        Account account = new Account();
        account.setId((Integer) out.get("id"));
        account.setBalance((Double) out.get("balance"));
        account.setCardNumber((Long) out.get("cardNumber"));
        account.setAccountNumber((Long) out.get("accountNumber"));
        account.setUser((Integer) out.get("user_Id"));
        account.setDateCreated((Date) out.get("date_created"));

        return account;
    }
}
