package com.example.realestateapis.serviceImpl;

import com.example.realestateapis.dto.PaystackDto;
import com.example.realestateapis.dto.RegisterDto;
import com.example.realestateapis.dto.SendConfirmationDto;
import com.example.realestateapis.exceptions.HandleTransactionFailedException;
import com.example.realestateapis.service.ConfirmationService;
import com.example.realestateapis.service.PaystackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaystackServiceImpl implements PaystackService {
    @Value("${paystack.secret.key}")
    private String secretKey;

    @Autowired
    private ConfirmationService confirmationService;

    private RegisterDto registerDto;

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
    public ResponseEntity<Map> verifyTransaction(String reference, PaystackDto paystackDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(secretKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response1 = restTemplate.exchange("https://api.paystack.co/transaction/verify/" + reference,
                HttpMethod.GET,
                entity,
                Map.class
        );

//        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        Map<String, Object> responseBody = response1.getBody();

        if (responseBody == null || !responseBody.containsKey("data")) {
            throw new RuntimeException("Invalid response from Paystack");
        }

        Map<String, Object> data = (Map<String, Object>) responseBody.get("data");

        Map<String, Object> customer = (Map<String, Object>) data.get("customer");


        String customerEmail = (String) customer.get("email");
//        System.out.println(customerEmail);
//        System.out.println(paystackDto.getEmail());
        if (!paystackDto.getEmail().equalsIgnoreCase(customerEmail)) {
            throw new RuntimeException("Email does not match transaction record");
        }

        if (data.get("status").equals("success")) {
            SendConfirmationDto sendConfirmationDto = new SendConfirmationDto();
            sendConfirmationDto.setEmail(paystackDto.getEmail());

            confirmationService.sendBooking(sendConfirmationDto);
        } else {
            throw new HandleTransactionFailedException("Payment was not successful");
        }


        return response1;
    }
}
