package com.example.realestateapis.serviceImpl;

import com.example.realestateapis.model.Property;
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
        // Create a new property
        Property newProperty = new Property();
        newProperty.setProperty_type(property.getProperty_type());
        newProperty.setProperty_price(property.getProperty_price());
        newProperty.setProperty_information(property.getProperty_information());
        newProperty.setProperty_image(property.getProperty_image());
        newProperty.setSize_of_property(property.getSize_of_property());
        newProperty.setNo_of_rooms(property.getNo_of_rooms());
        newProperty.setNo_of_bathrooms(property.getNo_of_bathrooms());
        newProperty.setEmail(property.getEmail());
        newProperty.setSold(false); // mark as unsold initially

        return propertyRepository.save(newProperty);
    }

    @Override
    public void buyProperty(String propertyId, String buyerEmail) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        if (property.isSold()) {
            throw new RuntimeException("Property is already sold!");
        }

        property.setEmail(buyerEmail);
        property.setSold(true);
        propertyRepository.save(property);
    }
}
