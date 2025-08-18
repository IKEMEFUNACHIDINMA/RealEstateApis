package com.example.realestateapis.serviceImpl;

import com.example.realestateapis.service.PaystackService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaystackServiceImpl implements PaystackService {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public PaystackServiceImpl(RestTemplateBuilder builder,
                               @Value("${paystack.secret.key}") String secretKey,
                               @Value("${paystack.base.url}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate = builder
                .defaultHeader("Authorization", "Bearer " + secretKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Override
    public Map initializeTransaction(String email, Long amountKobo) {
        String url = baseUrl + "/transaction/initialize";

        Map<String, Object> request = new HashMap<>();
        request.put("email", email);
        request.put("amount", amountKobo);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        return response.getBody();
    }
}
