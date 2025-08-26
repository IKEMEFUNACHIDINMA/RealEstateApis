package com.example.realestateapis.controller;

import com.example.realestateapis.model.Admin;
import com.example.realestateapis.model.Property;
import com.example.realestateapis.service.PropertyService;
import com.example.realestateapis.utils.Helper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    @Autowired
    private Helper helper;

    @GetMapping("/buy")
    public List<Property> getProperty() {

        return propertyService.getAllProperty();
    }

    @PostMapping("/create")
    public Property createProperty(@RequestBody Property property, HttpServletRequest request) throws BadRequestException {
        Admin loggedInUser = helper.extractLoggedInAdmin(request);
        System.out.println(loggedInUser.getUsername());
        return propertyService.createProperty(property, request);
    }

}
