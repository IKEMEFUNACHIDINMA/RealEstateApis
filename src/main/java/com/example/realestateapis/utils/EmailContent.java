package com.example.realestateapis.utils;

public class EmailContent {
    public static String registrationEmail(){
        return "<p> Dear user, </p>"+
                "<p> Your registration to Enulux has been successful! </p>";
    }

    public static String bookingEmail(){
        return "<p> Dear User, </p>"+
                "<p> Your booking purchase has been successful! </p>"+
                "<p> Happy Finding! </p>";
    }
}
