package com.interswitch.paytransact.daos.impl;

import com.interswitch.paytransact.daos.interfaces.HistoryDao;
import com.interswitch.paytransact.entities.Account;
import com.interswitch.paytransact.entities.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class HistoryDaoImpl implements HistoryDao {
    private SimpleJdbcCall
            create,
            getHistoryList;

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        create = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insert_into_history_table");
        getHistoryList = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("get_account_history");
    }

    @Override
    public void create(History history) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("account_id", history.getAccount())
                .addValue("body", history.getBody())
                .addValue("date_created", history.getDateCreated());

        this.create.execute(in);
    }

    @Override
    public List<History> getHistoryList(Account account) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("accountId", account.getId());
        return (List<History>) this.getHistoryList
                .execute(in)
                .get("#result-set-1");
    }
}
