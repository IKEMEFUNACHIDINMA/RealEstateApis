package com.example.realestateapis.repository;

import com.example.realestateapis.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

//import java.lang.ScopedValue;
import java.util.Optional;

public interface PropertyRepository extends MongoRepository<Property,String> {
    Optional<Property> findById(Long id);


}
