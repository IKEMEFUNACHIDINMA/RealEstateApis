package com.example.realestateapis.controller;


import com.example.realestateapis.dto.PropertyForSaleRequest;
import com.example.realestateapis.service.PropertyForSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/properties-for-sale")
@RequiredArgsConstructor
public class PropertyForSaleController {

    private final PropertyForSaleService propertyForSaleService;

    @PostMapping("/sell")
    public ResponseEntity<?> sellProperty(@RequestBody PropertyForSaleRequest request) {
        return ResponseEntity.ok(propertyForSaleService.createProperty(request));
    }
    @PutMapping("/approve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveProperty(@PathVariable String id) {
        return ResponseEntity.ok(propertyForSaleService.approveProperty(id));
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableProperties() {
        return ResponseEntity.ok(propertyForSaleService.getApprovedAvailableProperties());
    }

    @PutMapping("/sold/{id}")
    public ResponseEntity<?> markAsSold(@PathVariable String id,
                                        Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(propertyForSaleService.markAsSold(id, email));
    }

}
