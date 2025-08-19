package com.example.realestateapis.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String property_type;
    private String property_information;
    private String no_of_rooms;
    private String no_of_bathrooms;
    private String size_of_property;
    private String property_size;
    private String property_image;

    private String title;
    private Long price;
    private boolean isSold = false;
}
