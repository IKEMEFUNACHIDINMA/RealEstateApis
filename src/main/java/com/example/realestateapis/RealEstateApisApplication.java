package com.example.realestateapis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RealEstateApisApplication {

    public static void main(String[] args) {
//        Dotenv dotenv = Dotenv.load();
//        System.setProperty("SPRING_DATA_MONGODB_URI", dotenv.get("SPRING_DATA_MONGODB_URI"));
//        System.setProperty("SPRING_MAIL_PASSWORD", dotenv.get("SPRING_MAIL_PASSWORD"));
//        System.setProperty("PAYSTACK_SECRET_KEY", dotenv.get("PAYSTACK_SECRET_KEY"));

        SpringApplication.run(RealEstateApisApplication.class, args);
        System.out.println("Real Estate Api is running!");
    }

}
