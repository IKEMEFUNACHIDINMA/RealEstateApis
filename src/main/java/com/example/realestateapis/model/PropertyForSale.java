package com.example.realestateapis.model;

import com.example.realestateapis.enums.PropertyType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "property4sale")
public class PropertyForSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String fullName;
    private String emailAddress;
    private String phoneNumber;
    private String propertyLocation;
    private String askingPrice;
    private PropertyType propertyType;
    private String propertyDescription;
    private String propertyImage;

    private boolean approved = false;
    private String status = "pending";

    @ManyToOne
    private User seller;
}
