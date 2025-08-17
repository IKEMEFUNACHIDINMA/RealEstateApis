package com.example.realestateapis.service;

import java.util.Map;

public interface PaystackService {

    Map<String, Object> initializeTransaction(String email, Long amountKobo);
}
