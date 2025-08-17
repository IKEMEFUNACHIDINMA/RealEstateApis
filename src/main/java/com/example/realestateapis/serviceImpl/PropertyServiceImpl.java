package com.example.realestateapis.serviceImpl;

import com.example.realestateapis.model.Property;
import com.example.realestateapis.repository.PropertyRepository;
import com.example.realestateapis.service.PropertyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<Property> getAllProperty() {
        return propertyRepository.findAll();
    }

    @Override
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }
}
