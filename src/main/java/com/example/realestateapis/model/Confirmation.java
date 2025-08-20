package com.example.realestateapis.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Document(collection = "confirmation")
@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Confirmation {

    @Id
    private String id;

    private String email;
    private String phonenumber;
    @Indexed(name = "CreatedDateIndex", expireAfter = "5m")
    private Date createdAt = Date.from(Instant.now());
    private String userid;
}
