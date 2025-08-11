package com.example.realestateapis.exceptions;

public class HandleUserDoesNotExistException extends RuntimeException{
    public HandleUserDoesNotExistException(String message){
        super(message);
    }
}
