package com.interswitch.paytransact.services.impl;

import com.interswitch.paytransact.daos.interfaces.UserDao;
import com.interswitch.paytransact.dtos.SignupDto;
import com.interswitch.paytransact.entities.User;
import com.interswitch.paytransact.exceptions.MainExceptions;
import com.interswitch.paytransact.exceptions.NotFoundException;
import com.interswitch.paytransact.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Component
@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserDao userDao) {
        this.modelMapper = modelMapper;
        this.userDao = userDao;
    }

    @Override
    public User getUser(Integer id) throws NotFoundException {
        User user = userDao.findUserById(id);
        if (user.getId() == null) {
            throw new NotFoundException("could not find user with id " + id);
        }
        return user;
    }

    @Override
    public User loadUserByEmail(String email) throws NotFoundException {
        User user = userDao.findByEmail(email);
        if (user.getEmail() == null) {
            throw new NotFoundException(("could not find user with this email"));
        }
        return user;
    }

    @Override
    public void register(SignupDto signUpDto) throws MainExceptions {
        if (signUpDto.getEmail().isEmpty() || signUpDto.getPassword().isEmpty()) {
            throw new MainExceptions("email and password are required");
        } else if (userDao.existsByEmail(signUpDto.getEmail())) {
            throw new MainExceptions("account with email: " + signUpDto.getEmail() + " exists");
        }

        User user = modelMapper.map(signUpDto, User.class);
        user.setDateCreated(new Date());
        userDao.create(user);
    }
}
