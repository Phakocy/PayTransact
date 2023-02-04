package com.interswitch.paytransact.entities.commons;

import com.interswitch.paytransact.entities.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int line) throws SQLException {
        UserResultSetExtractor extractor = new UserResultSetExtractor();
        return extractor.extractData(rs);
    }
}
