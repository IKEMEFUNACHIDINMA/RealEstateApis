package com.example.realestateapis.exceptions;

public class HandleTransactionFailedException extends RuntimeException {
    public HandleTransactionFailedException(String message) {
        super(message);
    }
}
