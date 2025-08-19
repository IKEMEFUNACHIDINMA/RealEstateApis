package com.example.realestateapis.controller;


import com.example.realestateapis.model.Property;
import com.example.realestateapis.repository.PropertyRepository;
import com.example.realestateapis.service.PaystackService;
import com.example.realestateapis.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PaystackService  paystackService;

    public PaymentController(PaystackService paystackService, PropertyRepository propertyRepository) {
        this.paystackService = paystackService;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/buy/{propertyid}")
    public ResponseEntity<?> initializeTransaction(@PathVariable("propertyid") Long propertyid,
                                                @RequestParam String email) {
        Property property = propertyRepository.findById(propertyid)
                .orElseThrow(() -> new RuntimeException("property not found"));

        Long amountKobo = property.getPrice() * 100;


        Map<String, Object> response =
                paystackService.initializeTransaction(email, amountKobo);

        return ResponseEntity.ok("Property Purchased, Thank youu!");
    }
}
