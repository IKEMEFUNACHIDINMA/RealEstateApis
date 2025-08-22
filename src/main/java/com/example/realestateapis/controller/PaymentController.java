package com.example.realestateapis.controller;

import com.example.realestateapis.dto.PaystackDto;
import com.example.realestateapis.dto.SendConfirmationDto;
import com.example.realestateapis.model.Payment;
import com.example.realestateapis.model.Property;
import com.example.realestateapis.model.User;
import com.example.realestateapis.repository.PaymentRepository;
import com.example.realestateapis.repository.PropertyRepository;
import com.example.realestateapis.service.ConfirmationService;
import com.example.realestateapis.service.PaystackService;
import com.example.realestateapis.utils.Helper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaystackService paystackService;
    private final PropertyRepository propertyRepository;
    private final PaymentRepository paymentRepository;
    private final Helper helper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ConfirmationService confirmationService;

    public PaymentController(PaystackService paystackService,
                             PropertyRepository propertyRepository,
                             PaymentRepository paymentRepository, Helper helper) {
        this.paystackService = paystackService;
        this.propertyRepository = propertyRepository;
        this.paymentRepository = paymentRepository;
        this.helper = helper;
    }

    @Value("${paystack.secret.key}")
    private String secretKey;
    @PostMapping("/buy/{propertyId}")
    public ResponseEntity<?> initializeTransaction(@PathVariable String propertyId,
                                                   HttpServletRequest request) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));

        long amountKobo = property.getProperty_price() * 100;
        User buyer = helper.extractLoggedInUser(request);
        System.out.println(buyer.getEmail());

        Map<String, Object> response =
                paystackService.initializeTransaction(buyer.getEmail(), amountKobo);

        Map<String, Object> data = (Map<String, Object>) response.get("data");
        String reference = (String) data.get("reference");

        Payment payment = new Payment();
        payment.setEmail(buyer.getEmail());
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
//    @PostMapping("/verify/payment")
//    public ResponseEntity<Map<String, Object>> verifyPayment(
//            @RequestBody String reference,
//            @RequestBody String email) {
//
//        Map<String, Object> result = paystackService.verifyTransaction(reference, email);
//        return ResponseEntity.ok(result);
//    }

    @GetMapping("/verify/{reference}")
    public ResponseEntity<Map> verifyPayment(@PathVariable String reference, @RequestBody PaystackDto paystackDto) {
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
        System.out.println(customerEmail);
        System.out.println(paystackDto.getEmail());
        if (!paystackDto.getEmail().equalsIgnoreCase(customerEmail)) {
            throw new RuntimeException("Email does not match transaction record");
        }
        SendConfirmationDto sendConfirmationDto = new SendConfirmationDto();
        sendConfirmationDto.setEmail(paystackDto.getEmail());

        confirmationService.sendBooking(sendConfirmationDto);

        return response1;
    }
//        return response;


}
