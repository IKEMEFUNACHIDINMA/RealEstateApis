package com.example.realestateapis.serviceImpl;

import com.example.realestateapis.dto.EmailDto;
import com.example.realestateapis.dto.SendConfirmationDto;
import com.example.realestateapis.exceptions.HandleUserDoesNotExistException;
import com.example.realestateapis.model.Confirmation;
import com.example.realestateapis.model.User;
import com.example.realestateapis.repository.ConfirmationRepository;
import com.example.realestateapis.repository.UserRepository;
import com.example.realestateapis.service.ConfirmationService;
import com.example.realestateapis.service.EmailService;
import com.example.realestateapis.utils.EmailContent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmationServiceImpl implements ConfirmationService {
    @Autowired
    private final ConfirmationRepository confirmationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public String sendConfirmation(SendConfirmationDto sendConfirmationDto) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmailIgnoreCase(sendConfirmationDto.getEmail()).orElseThrow(() -> new HandleUserDoesNotExistException("User not found")));

        Confirmation confirmation = new Confirmation();
        confirmation.setEmail(sendConfirmationDto.getEmail());
        confirmation.setId(user.get().getId());
        confirmation.setPhonenumber(sendConfirmationDto.getPhonenumber());
        confirmationRepository.save(confirmation);

        String message = EmailContent.confirmationEmail();
        EmailDto emailDto = EmailDto.builder()
                .recipient(user.get().getEmail())
                .subject("Account Verification")
                .messageBody(message)
                .build();
        emailService.sendConfirmationEmail(emailDto);

        return "";
    }
}
