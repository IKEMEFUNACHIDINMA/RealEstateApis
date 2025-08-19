package com.example.realestateapis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

    @RestController
    public class TestController {
        @GetMapping()
        public String getStatus(){
            return "Real Estate-app is up and running, time: "+ LocalDateTime.now();
        }
    }

