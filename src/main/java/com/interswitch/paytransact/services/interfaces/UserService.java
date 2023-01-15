package com.interswitch.paytransact.services.interfaces;

import com.interswitch.paytransact.dtos.SignupDto;
import com.interswitch.paytransact.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUser(Long id);

    User loadUserByEmail(String email);

    void register(SignupDto signUpDto);
}
