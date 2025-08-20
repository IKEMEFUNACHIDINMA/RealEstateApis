package com.example.realestateapis.repository;

import com.example.realestateapis.model.PropertyForSale;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PropertyForSaleRepository extends MongoRepository<PropertyForSale, String> {
    List<PropertyForSale> findByApprovedTrueAndStatus(String status);
}
