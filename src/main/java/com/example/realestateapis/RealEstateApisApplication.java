package com.example.realestateapis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RealEstateApisApplication {

    public static void main(String[] args) {

        SpringApplication.run(RealEstateApisApplication.class, args);
        System.out.println("Real Estate Api is running!");
    }

}
