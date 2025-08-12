package com.example.realestateapis.serviceImpl;

import com.example.realestateapis.dto.Logindto;
import com.example.realestateapis.dto.RegisterDto;
import com.example.realestateapis.exceptions.HandleUserDoesNotExistException;
import com.example.realestateapis.model.User;
import com.example.realestateapis.repository.UserRepository;
import com.example.realestateapis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterDto registerDto) {
        User newUser = new User();
        newUser.setUsername(registerDto.getUsername());
        String password = passwordEncoder.encode(registerDto.getPassword());
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        System.out.println(password);
        newUser.setEmail(registerDto.getEmail());
        newUser.setPhonenumber(registerDto.getPhonenumber());

        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public String login(Logindto user) {
        User existing = userRepository.findByEmailIgnoreCase(String.valueOf(user.getEmail()))
                .orElseThrow(() -> new HandleUserDoesNotExistException("User does not exist"));


         if (!passwordEncoder.matches(user.getPassword(), existing.getPassword())) {
            throw new HandleUserDoesNotExistException("Invalid password");
        }
        return "Login successful";

    }
}
