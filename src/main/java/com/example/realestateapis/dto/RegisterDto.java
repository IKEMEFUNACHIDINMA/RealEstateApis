package com.example.realestateapis.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class RegisterDto {

    private String surname;
    private String firstname;
    private String email;
    private String password;
    private String phonenumber;
    private String username;

}
