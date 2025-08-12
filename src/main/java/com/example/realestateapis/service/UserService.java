package com.example.realestateapis.service;

import com.example.realestateapis.dto.RegisterDto;
import com.example.realestateapis.model.User;

public interface UserService {
    //REGISTER SIGN UP
    User registerUser(RegisterDto registerDto);

}
