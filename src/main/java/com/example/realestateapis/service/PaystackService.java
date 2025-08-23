package com.example.realestateapis.service;

import com.example.realestateapis.dto.PaystackDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PaystackService {
    Map<String, Object> initializeTransaction(String email, Long amountKobo);
    ResponseEntity<Map> verifyTransaction(String reference, PaystackDto paystackDto);
}
