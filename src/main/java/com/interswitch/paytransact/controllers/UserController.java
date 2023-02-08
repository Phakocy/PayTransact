package com.interswitch.paytransact.controllers;

import com.interswitch.paytransact.dtos.SignupDto;
import com.interswitch.paytransact.entities.commons.ApiResponse;
import com.interswitch.paytransact.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/info/{uid}")
    ResponseEntity<ApiResponse> getUser(@PathVariable(name = "uid") Integer userId) {
        return new ResponseEntity<>(new ApiResponse(userService.getUser(userId)), HttpStatus.OK);
    }

    @PostMapping("register")
    ResponseEntity<ApiResponse> register(@RequestBody SignupDto signUpDto) {
        userService.register(signUpDto);
        return new ResponseEntity<>(new ApiResponse("registered successfully - proceed to login."), HttpStatus.CREATED);
    }
}
