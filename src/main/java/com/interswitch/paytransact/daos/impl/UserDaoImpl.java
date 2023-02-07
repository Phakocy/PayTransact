package com.interswitch.paytransact.daos.impl;

import com.interswitch.paytransact.daos.interfaces.UserDao;
import com.interswitch.paytransact.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Configuration
public class UserDaoImpl implements UserDao {
    private SimpleJdbcCall
            create,
            findByEmail,
            findById;

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        create = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insert_into_user_table");
        findByEmail = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("find_user_by_email");
        findById = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("find_user_by_id");
    }

    @Override
    public List<User> select() {
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        User user = findByEmail(email);
        return user.getEmail() != null;
    }

    @Override
    public void create(User user) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("date_created", user.getDateCreated());
        this.create.execute(in);
    }

    @Override
    public User findByEmail(String email) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("_email", email);
        return setUserObject(this.findByEmail.execute(in));
    }

    @Override
    public User findUserById(Integer id) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("_id", id);
        return setUserObject(this.findById.execute(in));
    }

    private User setUserObject(Map<String, Object> out) {
        User user = new User();
        user.setId((Integer) out.get("id"));
        user.setEmail((String) out.get("email"));
        user.setDateCreated((Date) out.get("date_created"));

        return user;
    }
}
