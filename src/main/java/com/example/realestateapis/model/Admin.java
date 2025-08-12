package com.example.realestateapis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

@Document
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Admin{
    @Id
    private String id;
    private String email;
    private String password;
}
