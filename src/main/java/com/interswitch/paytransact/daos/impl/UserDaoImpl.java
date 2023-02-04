package com.interswitch.paytransact.daos.impl;

import com.interswitch.paytransact.daos.interfaces.UserDao;
import com.interswitch.paytransact.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Configuration
public class UserDaoImpl implements UserDao {
    private SimpleJdbcCall findByEmail;

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        findByEmail = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("find_user_by_email");
    }

    @Override
    public List<User> select() {
        return null;
    }

    @Override
    public boolean existByEmail(String email) throws DataAccessException {

        return false;
    }

    @Override
    public void create(User user) {

    }

    @Override
    public void findByEmail(String email) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("email", email);
        Map<String, Object> out = this.findByEmail.execute(in);

//        User user = new User();
//        user.setEmail();

        System.out.println("value of email =====>>>" + out);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.empty();
    }
}
