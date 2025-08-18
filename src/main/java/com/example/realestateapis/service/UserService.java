package com.example.realestateapis.service;

import com.example.realestateapis.dto.Logindto;
import com.example.realestateapis.dto.RegisterDto;
import com.example.realestateapis.model.User;

public interface UserService {
    //REGISTER SIGN UP
    User registerUser(RegisterDto registerDto);

    //LOGIN
    String login (Logindto user);

}
