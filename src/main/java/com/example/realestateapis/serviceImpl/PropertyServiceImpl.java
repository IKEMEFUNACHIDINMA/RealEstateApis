package com.example.realestateapis.serviceImpl;

import com.example.realestateapis.model.Admin;
import com.example.realestateapis.model.Property;
import com.example.realestateapis.model.User;
import com.example.realestateapis.repository.PropertyRepository;
import com.example.realestateapis.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public List<Property> getAllProperty() {
        return propertyRepository.findAll();
    }

    @Override
    public Property createProperty(Property property) {
        User user = new User();
        if (!isAdmin(user)) {
            System.out.println("Access Denied!");
        }
        Property newProperty = new Property();
        newProperty.setProperty_type(property.getProperty_type());
        newProperty.setProperty_price(property.getProperty_price());
        newProperty.setProperty_information(property.getProperty_information());
        newProperty.setProperty_image(property.getProperty_image());
        newProperty.setSize_of_property(property.getSize_of_property());
        newProperty.setNo_of_rooms(property.getNo_of_rooms());
        newProperty.setNo_of_bathrooms(property.getNo_of_bathrooms());

        propertyRepository.save(newProperty);
        return newProperty;
    }

    private boolean isAdmin(User user){
        return true;
    }

}
