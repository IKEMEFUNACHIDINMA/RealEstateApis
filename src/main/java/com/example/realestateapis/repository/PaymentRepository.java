package com.example.realestateapis.repository;

import com.example.realestateapis.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    List<Payment> findByEmail(String email);
}
