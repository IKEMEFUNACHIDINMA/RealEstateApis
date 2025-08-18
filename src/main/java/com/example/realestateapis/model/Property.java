package com.example.realestateapis.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "properties")
public class Property {
    @Id
    private String id;

    @NotNull
    private String property_type;

   @Email
   private String email;

    @NotNull
    private String property_information;

    @NotNull
    private String no_of_rooms;

    @NotNull
    private String no_of_bathrooms;

    @NotNull
    private String size_of_property;

    @NotNull
    private Long property_price;

    @NotNull
    private String property_image;

    private String title;
    private Long price;
    private boolean isSold = false;
}
