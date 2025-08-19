package com.example.realestateapis.controller;

import com.example.realestateapis.model.Property;
import com.example.realestateapis.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/get")
    public List<Property> getProperty() {
        return propertyService.getAllProperty();
    }

    @PostMapping("/create")
    public Property createProperty(@RequestBody Property property) {
        return propertyService.createProperty(property);
    }
//
//    @PostMapping("/buy/{id}")
//    public ResponseEntity<?> buyProperty(@PathVariable String id, Principal principal) {
//        String buyerEmail = principal.getName(); // email from JWT
//        propertyService.buyProperty(id, buyerEmail);
//        return ResponseEntity.ok("Property bought successfully by " + buyerEmail);
//    }
}
