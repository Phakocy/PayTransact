package com.interswitch.paytransact.daos.interfaces;

import com.interswitch.paytransact.entities.User;

import java.util.List;

public interface UserDao extends BaseDao {
    List<User> select();

    boolean existsByEmail(String email);

    void create(User user);

    User findByEmail(String email);

    User findUserById(Integer id);
}
