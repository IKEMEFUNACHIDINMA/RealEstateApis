package com.example.realestateapis.serviceImpl;

import com.example.realestateapis.dto.Logindto;
import com.example.realestateapis.model.Admin;
import com.example.realestateapis.repository.AdminRepository;
import com.example.realestateapis.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String AdminLogin(Logindto admin) {
        Admin existing = adminRepository.findByEmailIgnoreCase(String.valueOf(admin.getEmail()))
                .orElse(null);
        String result = "Admin was not found";
        if (existing != null) {
            if (!passwordEncoder.matches(admin.getPassword(), existing.getPassword())) {
                result = "Incorrect password";
            } else {
                result = "Successfully logged in";
            }
        }
        return result;
    }
}