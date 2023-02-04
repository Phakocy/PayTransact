package com.interswitch.paytransact.daos.interfaces;

import com.interswitch.paytransact.entities.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    void setDataSource(DataSource dataSource);
    List<User> select();
    boolean existByEmail(String email);
    void create(User user);
    void findByEmail(String email);
    Optional<User> findUserById(Long id);
}
