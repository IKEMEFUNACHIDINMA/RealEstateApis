package com.example.realestateapis.controller;

import com.example.realestateapis.dto.RegisterDto;
import com.example.realestateapis.model.User;
import com.example.realestateapis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userservice;

    @PostMapping("/signup")
    public User registerUser(@RequestBody RegisterDto registerDto) {
        userservice.registerUser(registerDto);
        return registerDto;
    }

}
