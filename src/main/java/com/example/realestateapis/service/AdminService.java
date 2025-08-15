package com.example.realestateapis.service;
import com.example.realestateapis.dto.Logindto;
import com.example.realestateapis.model.Admin;

public interface AdminService {
    Admin registerAdmin(Admin admin);
    String AdminLogin(Logindto admin);
}