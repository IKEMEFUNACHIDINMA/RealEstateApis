package com.example.realestateapis.service;

import com.example.realestateapis.model.Admin;

public interface AdminService {
    //REGISTER ADMIN
    Admin registerAdmin(Admin admin);
    // ADMIN LOGIN
    String adminLogin(Admin admin);
}
