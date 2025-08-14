package com.example.realestateapis.controller;

import com.example.realestateapis.model.Property;
import com.example.realestateapis.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/getProperties")
    public List<Property> getProperty() {
        return propertyService.getAllProperty();
    }

    @PostMapping("/createProperty")
    public Property createProperty(@RequestBody Property property) {
        return propertyService.createProperty(property);
    }

}
