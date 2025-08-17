package com.example.realestateapis.controller;

import com.example.realestateapis.model.Property;
import com.example.realestateapis.repository.PropertyRepository;
import com.example.realestateapis.service.PaystackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaystackService paystackService;
    private final PropertyRepository propertyRepository;

    public PaymentController(PaystackService paystackService,
                             PropertyRepository propertyRepository) {
        this.paystackService = paystackService;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/buy/{propertyId}")
    public ResponseEntity<?> initializeTransaction(@PathVariable Long propertyId,
                                                   @RequestParam String email) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));

        long amountKobo = property.getPrice() * 100;

        Map<String, Object> response =
                paystackService.initializeTransaction(email, amountKobo);

        return ResponseEntity.ok(response);
    }

}
