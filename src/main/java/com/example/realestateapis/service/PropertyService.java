package com.example.realestateapis.service;

import com.example.realestateapis.model.Property;

import java.util.List;

public interface PropertyService {

    List<Property> getAllProperty();


    Property createProperty(Property property);

    void buyProperty(Long propertyId, String buyerEmail);
}
