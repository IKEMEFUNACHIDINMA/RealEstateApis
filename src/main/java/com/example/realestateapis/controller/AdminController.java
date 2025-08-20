package com.example.realestateapis.controller;

import com.example.realestateapis.dto.Logindto;
import com.example.realestateapis.model.Admin;
import com.example.realestateapis.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/signup")
    public Admin registerAdmin(@RequestBody Admin admin){
        return adminService.registerAdmin(admin);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Logindto admin){
        String token = adminService.adminLogin(admin);
        return ResponseEntity.ok(token);
    }
}
