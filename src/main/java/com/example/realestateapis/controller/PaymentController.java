package com.example.realestateapis.controller;

import com.example.realestateapis.model.Payment;
import com.example.realestateapis.model.Property;
import com.example.realestateapis.repository.PaymentRepository;
import com.example.realestateapis.repository.PropertyRepository;
import com.example.realestateapis.service.PaystackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaystackService paystackService;
    private final PropertyRepository propertyRepository;
    private final PaymentRepository paymentRepository;

    public PaymentController(PaystackService paystackService,
                             PropertyRepository propertyRepository,
                             PaymentRepository paymentRepository) {
        this.paystackService = paystackService;
        this.propertyRepository = propertyRepository;
        this.paymentRepository = paymentRepository;
    }

    @PostMapping("/buy/{propertyId}")
    public ResponseEntity<?> initializeTransaction(@PathVariable String propertyId,
                                                   Authentication authentication) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));

        long amountKobo = property.getProperty_price() * 100;
        String buyerEmail = authentication.getName();

        Map<String, Object> response =
                paystackService.initializeTransaction(buyerEmail, amountKobo);

        Map<String, Object> data = (Map<String, Object>) response.get("data");
        String reference = (String) data.get("reference");

        Payment payment = new Payment();
        payment.setEmail(buyerEmail);
        payment.setAmount(amountKobo);
        payment.setStatus("pending");
        payment.setReference(reference);
        payment.setProperty(property);

        paymentRepository.save(payment);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/my-purchases")
    public ResponseEntity<?> getMyPurchases(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("You must be logged in to view purchases");
        }

        String email = authentication.getName();

        List<Payment> payments = paymentRepository.findByEmail(email);

        return ResponseEntity.ok(payments);
    }

}
