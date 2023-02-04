package com.interswitch.paytransact.entities.commons;

import com.interswitch.paytransact.entities.User;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserResultSetExtractor implements ResultSetExtractor {
    @Override
    public User extractData(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setDateCreated(new Date());
        return user;
    }
}
