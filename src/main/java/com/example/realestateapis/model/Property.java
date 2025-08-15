package com.example.realestateapis.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Document(collection = "property_db")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank
    private String property_type;
    @NotBlank
    private String property_information;
    @NotBlank
    private String no_of_rooms;
    @NotBlank
    private String no_of_bathrooms;
    @NotBlank
    private String size_of_property;
    @NotBlank
    private String property_price;
    @NotBlank
    private String property_image;
}
