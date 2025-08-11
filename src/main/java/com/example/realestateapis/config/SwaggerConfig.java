package com.example.realestateapis.config;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI realestateApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Realestate API")
                        .description("Realestate API application documentaion")
                        .version("v1.0"));
    }
}
