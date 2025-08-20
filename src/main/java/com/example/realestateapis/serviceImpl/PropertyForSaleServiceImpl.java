package com.example.realestateapis.serviceImpl;

import com.example.realestateapis.dto.PropertyForSaleRequest;
import com.example.realestateapis.model.PropertyForSale;
import com.example.realestateapis.repository.PropertyForSaleRepository;
import com.example.realestateapis.service.PropertyForSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyForSaleServiceImpl implements PropertyForSaleService {

    private final PropertyForSaleRepository propertyForSaleRepository;

    @Override
    public PropertyForSale createProperty(PropertyForSaleRequest request) {
        PropertyForSale property = new PropertyForSale();
        property.setFullName(request.getFullName());
        property.setEmailAddress(request.getEmailAddress());
        property.setPhoneNumber(request.getPhoneNumber());
        property.setPropertyLocation(request.getPropertyLocation());
        property.setAskingPrice(request.getAskingPrice());
        property.setPropertyType(request.getPropertyType());
        property.setPropertyDescription(request.getPropertyDescription());
        property.setPropertyImage(request.getPropertyImage());
        property.setApproved(false);
        property.setStatus("pending");

        return propertyForSaleRepository.save(property);
    }

    @Override
    public PropertyForSale approveProperty(String id) {
        Long propertyId = Long.parseLong(id); // since DB id is Long
        PropertyForSale property = propertyForSaleRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        property.setApproved(true);
        property.setStatus("available");

        return propertyForSaleRepository.save(property);
    }

    @Override
    public List<PropertyForSale> getApprovedAvailableProperties() {
        return propertyForSaleRepository.findByApprovedTrueAndStatus("available");
    }

    @Override
    public PropertyForSale markAsSold(String id, String email) {
        Long propertyId = Long.parseLong(id);
        PropertyForSale property = propertyForSaleRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        if (!property.getEmailAddress().equalsIgnoreCase(email)) {
            throw new RuntimeException("You are not the owner of this property");
        }

        property.setStatus("sold");
        return propertyForSaleRepository.save(property);
    }
}
