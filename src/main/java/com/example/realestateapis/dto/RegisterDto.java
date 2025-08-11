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

    public String username;
    public String email;
    public String password;
    public String phonenumber;

}
