package com.example.realestateapis.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "payments")
public class Payment {

    @Id
    private String id;

    private String email;
    private Long amount;
    private String status;
    private String reference;
    private LocalDateTime createdAt = LocalDateTime.now();

    @DBRef
    private Property property;
}
