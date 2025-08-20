package com.example.realestateapis.service;

import com.example.realestateapis.dto.SendConfirmationDto;

public interface ConfirmationService {
    String sendRegistration(SendConfirmationDto sendConfirmationDto);

    String sendBooking(SendConfirmationDto  sendConfirmationDto);
}
