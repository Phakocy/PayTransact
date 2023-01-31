package com.interswitch.paytransact.daos.interfaces;

import com.interswitch.paytransact.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> selectUsers();
    boolean existByEmail(String email);
    int insertUser(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findUserById(Long id);
}
