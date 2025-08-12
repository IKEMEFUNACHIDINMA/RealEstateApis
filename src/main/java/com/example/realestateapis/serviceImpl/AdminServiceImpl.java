package com.example.realestateapis.serviceImpl;

import com.example.realestateapis.dto.Logindto;
import com.example.realestateapis.model.Admin;
import com.example.realestateapis.repository.AdminRepository;
import com.example.realestateapis.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public String AdminLogin(Logindto admin) {
        Admin existing = adminRepository.findByEmailIgnoreCase(String.valueOf(admin.getEmail()))
                .orElse(null);
        String result = "User details was not found";
        if (existing != null) {
            if(existing.getPassword().equals(admin.getPassword())) {
                result = "User successfully logged in";
            }
            else {
                result = "User details not found";
            }
        }
        return result;
    }
}