package com.example.realestateapis.repository;

import com.example.realestateapis.model.Property;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaystackRepository extends MongoRepository<Property,Long> {
}
