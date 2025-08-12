package com.example.realestateapis.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Logindto {
    private String email;
    public String getEmail(){
            return email;
        }
        public void setEmail(String email){
            this.email = email;
        }
        public String getPassword(){
            return password;
        }
        public void setPassword(String password){
            this.password = password;
        }
        private String password;

    }
