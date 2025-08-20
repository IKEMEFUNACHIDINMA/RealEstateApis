package com.example.realestateapis.serviceImpl;

import com.example.realestateapis.enums.UserType;
import com.example.realestateapis.model.Admin;
import com.example.realestateapis.model.Property;
import com.example.realestateapis.model.User;
import com.example.realestateapis.repository.PropertyRepository;
import com.example.realestateapis.service.PropertyService;
import com.example.realestateapis.utils.Helper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private Helper helper;

    @Override
    public List<Property> getAllProperty() {
        return propertyRepository.findAll();
    }

    @Override
    public Property createProperty(Property property, HttpServletRequest request) throws BadRequestException {

        Admin loggedInUser = helper.extractLoggedInAdmin(request);

//        User user = new User();

        if (!(loggedInUser.getUserType() == UserType.ADMIN)) {
            throw new BadRequestException("Sorry only administrators can create a property");
        }
        Property newProperty = new Property();
        newProperty.setProperty_type(property.getProperty_type());
        newProperty.setProperty_price(property.getProperty_price());
        newProperty.setProperty_information(property.getProperty_information());
        newProperty.setProperty_image(property.getProperty_image());
        newProperty.setSize_of_property(property.getSize_of_property());
        newProperty.setNo_of_rooms(property.getNo_of_rooms());
        newProperty.setNo_of_bathrooms(property.getNo_of_bathrooms());
        newProperty.setEmail(property.getEmail());

        propertyRepository.save(newProperty);
        return newProperty;
    }

    private boolean isAdmin(User user){
        return true;
    }

}
