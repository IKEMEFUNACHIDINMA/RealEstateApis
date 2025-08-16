package com.example.realestateapis.repository;

import com.example.realestateapis.dto.SendConfirmationDto;
import com.example.realestateapis.model.Confirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ConfirmationRepository extends MongoRepository<Confirmation, String> {
    Optional<Confirmation> findById(String confirmation);
}
