package com.example.realestateapis.controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

public class TestController {
    @GetMapping()
    public String getStatus(){
        return "Real Estate-app is up and running, time: "+ LocalDateTime.now();
    }
}
