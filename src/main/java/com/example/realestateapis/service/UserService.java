package com.example.realestateapis.service;

import com.example.realestateapis.dto.Logindto;
import com.example.realestateapis.dto.RegisterDto;

public interface UserService {
    //REGISTER
    RegisterDto registerUser(RegisterDto registerDto);

    String login (Logindto user);

}
