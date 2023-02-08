package com.interswitch.paytransact.controllers;

import com.interswitch.paytransact.dtos.LoginDto;
import com.interswitch.paytransact.entities.commons.ApiResponse;
import com.interswitch.paytransact.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class AuthController {

    @Autowired
    public AuthController(UserService userService) {
    }

    @PostMapping("authenticate")
    ResponseEntity<ApiResponse> authenticate(@RequestBody LoginDto loginDto) {

        return new ResponseEntity<>(new ApiResponse(""), HttpStatus.OK);
    }
}
