package com.example.websitesecondhand.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super("User is not found");
    }
}
