package com.example.realestateapis.controller;

import com.example.realestateapis.dto.Logindto;
import com.example.realestateapis.dto.RegisterDto;
import com.example.realestateapis.model.Property;
import com.example.realestateapis.model.User;
import com.example.realestateapis.service.PropertyService;
import com.example.realestateapis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userservice;

    @PostMapping("/signup")
    public RegisterDto registerUser(@RequestBody RegisterDto registerDto) {
        userservice.registerUser(registerDto);
        return registerDto;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Logindto user) {
        String token = userservice.login(user);
        return ResponseEntity.ok(token);
    }

}
