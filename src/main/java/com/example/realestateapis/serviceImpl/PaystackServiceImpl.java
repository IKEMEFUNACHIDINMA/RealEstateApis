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
    @Override
    public Map<String, Object> verifyTransaction(String reference, String email) {
        String url = baseUrl + "/transaction/verify/" + reference;

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        Map<String, Object> responseBody = response.getBody();

        if (responseBody == null || !responseBody.containsKey("data")) {
            throw new RuntimeException("Invalid response from Paystack");
        }

        Map<String, Object> data = (Map<String, Object>) responseBody.get("data");

        Map<String, Object> customer = (Map<String, Object>) data.get("customer");
        String customerEmail = (String) customer.get("email");

        if (!email.equalsIgnoreCase(customerEmail)) {
            throw new RuntimeException("Email does not match transaction record");
        }

        return responseBody;
    }
}
