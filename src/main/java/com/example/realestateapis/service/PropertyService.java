package com.example.realestateapis.service;

import com.example.realestateapis.model.Property;

import java.util.List;

public interface PropertyService {
    //GET ALL
    List<Property> getAllProperty();

    //CREATE PROPERTY
    Property createProperty(Property property);


}
