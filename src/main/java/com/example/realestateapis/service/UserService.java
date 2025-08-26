package com.example.realestateapis.service;

import com.example.realestateapis.dto.LoginResponseDto;
import com.example.realestateapis.dto.Logindto;
import com.example.realestateapis.dto.RegisterDto;
import com.example.realestateapis.model.User;

//import java.lang.ScopedValue;
import java.util.Optional;

public interface UserService {
    //REGISTER SIGN UP
    User registerUser(RegisterDto registerDto);

    //LOGIN
    LoginResponseDto login (Logindto user);
    Optional<User> findByEmailIgnoreCase(String email);

}
