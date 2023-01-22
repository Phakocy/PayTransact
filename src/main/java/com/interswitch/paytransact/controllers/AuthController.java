package com.interswitch.paytransact.controllers;

import com.interswitch.paytransact.dtos.LoginDto;
import com.interswitch.paytransact.entities.commons.ApiResponse;
import com.interswitch.paytransact.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("authenticate")
    ResponseEntity<ApiResponse> authenticate(@RequestBody LoginDto loginDto){

        return new ResponseEntity<>(new ApiResponse(""), HttpStatus.OK);
    }
}
