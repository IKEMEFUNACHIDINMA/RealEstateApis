package com.example.realestateapis.service;
import com.example.realestateapis.dto.Logindto;
import com.example.realestateapis.model.Admin;

public interface AdminService {
    String AdminLogin(Logindto admin);
}