package com.interswitch.paytransact.services.interfaces;

import com.interswitch.paytransact.dtos.SignupDto;
import com.interswitch.paytransact.entities.User;

public interface UserService {
    User getUser(Integer id);

    User loadUserByEmail(String email);

    void register(SignupDto signUpDto);
}
