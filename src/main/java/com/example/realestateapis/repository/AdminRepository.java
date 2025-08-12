package com.example.realestateapis.repository;
import com.example.realestateapis.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<Admin,String> {
    Optional<Admin> findByEmailIgnoreCase(String email);
}