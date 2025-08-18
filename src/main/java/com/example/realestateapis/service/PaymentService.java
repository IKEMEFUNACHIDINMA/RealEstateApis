package com.example.realestateapis.service;

import reactor.core.publisher.Mono;
import java.util.Map;

public interface PaymentService {
    public Mono<Map<String, Object>> verifyPayment(String reference);
}
