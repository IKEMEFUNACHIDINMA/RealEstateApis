package com.example.realestateapis.repository;

import com.example.realestateapis.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin> findByEmailIgnoreCase(String email);
}
