package com.example.realestateapis.service;

import com.example.realestateapis.dto.PropertyForSaleRequest;
import com.example.realestateapis.model.PropertyForSale;

import java.util.List;
public interface PropertyForSaleService {
    PropertyForSale createProperty(PropertyForSaleRequest request);
    PropertyForSale approveProperty(String id);
    List<PropertyForSale> getApprovedAvailableProperties();
    PropertyForSale markAsSold(String id, String email);
}



