package com.example.realestateapis.serviceImpl;

import com.example.realestateapis.dto.Logindto;
import com.example.realestateapis.exceptions.HandleUserDoesNotExistException;
import com.example.realestateapis.model.Admin;
import com.example.realestateapis.repository.AdminRepository;
import com.example.realestateapis.service.AdminService;
import jakarta.persistence.Access;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    @Override
    public Admin registerAdmin(Admin admin) {
        Admin newAdmin = new Admin();
        newAdmin.setSurname(admin.getSurname());
        newAdmin.setFirstname(admin.getFirstname());
        newAdmin.setEmail(admin.getEmail());

        newAdmin.setPhonenumber(admin.getPhonenumber());
        String password = passwordEncoder.encode(admin.getPassword());
        newAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));
        newAdmin.setUserType(admin.getUserType());
        System.out.println(password);

        adminRepository.save(newAdmin);
        return newAdmin;
    }
    @Override
    public String adminLogin (Logindto admin){
        Admin existing = adminRepository.findByEmailIgnoreCase(String.valueOf(admin.getEmail()))
                .orElseThrow(() -> new HandleUserDoesNotExistException("Admin not found"));

        if (!passwordEncoder.matches(admin.getPassword(), existing.getPassword())) {
            throw new HandleUserDoesNotExistException("Invalid Password");
        }

        return jwtServiceImpl.generateToken((UserDetails) existing);
    }
}
