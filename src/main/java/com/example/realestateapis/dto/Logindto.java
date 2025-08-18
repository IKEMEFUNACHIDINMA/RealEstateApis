package com.example.realestateapis.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Logindto {
    private String email;

    private String password;

    }
