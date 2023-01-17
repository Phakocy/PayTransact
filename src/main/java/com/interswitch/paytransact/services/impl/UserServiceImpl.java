package com.interswitch.paytransact.services.impl;

import com.interswitch.paytransact.dtos.SignupDto;
import com.interswitch.paytransact.entities.User;
import com.interswitch.paytransact.exceptions.MainExceptions;
import com.interswitch.paytransact.exceptions.NotFoundException;
import com.interswitch.paytransact.repos.UserRepository;
import com.interswitch.paytransact.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Optional;

@Component
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<User> getUser(Long id) throws NotFoundException {
        Optional<User> user = userRepository.findUserById(id);
        if (user.isEmpty()) throw new NotFoundException("could not find user with ID " + id);
        return user;
    }

    @Override
    public User loadUserByEmail(String email) throws NotFoundException {
        Optional<User> userDetails = Optional.ofNullable(userRepository.findByEmail(email).orElseThrow(() -> (new NotFoundException("could not find user with this email"))));
        return userDetails.get();
    }

    @Override
    public void register(SignupDto signUpDto) throws MainExceptions {
        if (StringUtils.isEmpty(signUpDto.getEmail()) || StringUtils.isEmpty(signUpDto.getPassword())) {
            throw new MainExceptions("email and password are required");
        } else if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new MainExceptions("account with email: " + signUpDto.getEmail() + " exists");
        }
        User user = modelMapper.map(signUpDto, User.class);
        user.setDateCreated(new Date());
        userRepository.save(user);
    }
}
