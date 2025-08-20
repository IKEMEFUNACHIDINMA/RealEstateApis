package com.example.realestateapis.model;

import com.example.realestateapis.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "admin_db")
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String adminid;

    @Column(unique = true)
    private String email;

    @NotBlank
    private String surname;

    @NotBlank
    private String firstname;

    private UserType userType;

    @NotBlank
    @Size(max = 10, message = "password must be up to 8 characters")
    private String password;

    private String phonenumber;

}
