package com.example.realestateapis.dto;

import com.example.realestateapis.enums.PropertyType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PropertyForSaleRequest {
    private String FullName;
    private String EmailAddress;
    private String PhoneNumber;
    private String PropertyLocation;
    private String AskingPrice;
    private PropertyType propertyType;
    private String PropertyDescription;
    private String PropertyImage;

}
