package com.example.realestateapis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendConfirmationDto {
    private String email;
    private String phonenumber;
}
