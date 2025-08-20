package com.example.realestateapis.service;

import com.example.realestateapis.model.Property;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface PropertyService {
    //GET ALL
    List<Property> getAllProperty();

    //CREATE PROPERTY
    Property createProperty(Property property, HttpServletRequest request) throws BadRequestException;


}
