package com.example.realestateapis.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@RequiredArgsConstructor
public class Admin{
    @Id
    private String adminid;

    @Column(unique= true)
    private String email;

    @NotBlank
    private String surname;

    @NotBlank
    private String firstname;

    @NotBlank
    @Size(max=10, message = "password must be up to 8 characters")
    private String password;

    private String phonenumber;
}
