package com.example.realestateapis.service;

import com.example.realestateapis.dto.EmailDto;

public interface EmailService {
    void sendConfirmationEmail(EmailDto emailDto);
}
