package com.example.realestateapis.service;

import com.example.realestateapis.dto.SendConfirmationDto;

public interface ConfirmationService {
    String sendConfirmation(SendConfirmationDto sendConfirmationDto);
}
