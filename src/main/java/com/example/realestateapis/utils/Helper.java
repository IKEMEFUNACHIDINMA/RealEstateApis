package com.example.realestateapis.utils;

import com.example.realestateapis.exceptions.HandleUserDoesNotExistException;
import com.example.realestateapis.model.Admin;
import com.example.realestateapis.model.User;
import com.example.realestateapis.repository.AdminRepository;
import com.example.realestateapis.repository.UserRepository;
import com.example.realestateapis.serviceImpl.JwtServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Helper {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private JwtServiceImpl jwtService;



    public User extractLoggedInUser(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);
        String email = jwtService.extractUsername(jwt);
        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(()-> new HandleUserDoesNotExistException("Customer not found"));
    }

    public Admin extractLoggedInAdmin(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new SecurityException("Missing or invalid Authorization header");
        }

        String jwt = authHeader.substring(7);
        String email = jwtService.extractUsername(jwt);

        return adminRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new HandleUserDoesNotExistException("Admin with email '" + email + "' not found"));
    }

}
