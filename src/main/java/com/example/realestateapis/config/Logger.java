package com.example.realestateapis.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Logger {

       @Value("${spring.data.mongodb.uri}")
       private String mongoUri;

       @PostConstruct
       public void init() {
           System.out.println("MongoDB URI: " + mongoUri);
       }
}
