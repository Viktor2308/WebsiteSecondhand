package com.example.websitesecondhand.exception;

public final class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super("User is not found");
    }
}
