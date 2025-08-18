package com.example.realestateapis.serviceImpl;

import com.example.realestateapis.dto.EmailDto;
import com.example.realestateapis.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javamailSender;

    private static final String senderName = "Property link.ng";

    @Value("${mail.from.address}")
    private String fromAddress;

    @Value("${mail.from.address}")
    private String senderEmail;

    @Override
    public void sendConfirmationEmail(EmailDto emailDto) {
        try{
            MimeMessage message = javamailSender.createMimeMessage();
            var mailMessage = new MimeMessageHelper(message);
            mailMessage.setFrom(fromAddress, senderName);
//            mailMessage.setTo(emailDto.getRecipient());
            mailMessage.setSubject(emailDto.getSubject());
//            mailMessage.setText(emailDto.getMessageBody(), true);
            javamailSender.send(message);

        } catch (MessagingException | UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }

    }
}
