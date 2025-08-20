package com.example.realestateapis.repository;

import com.example.realestateapis.model.PropertyForSale;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PropertyForSaleRepository extends MongoRepository<PropertyForSale, Long> {
    List<PropertyForSale> findByApprovedTrueAndStatus(String status);
}
