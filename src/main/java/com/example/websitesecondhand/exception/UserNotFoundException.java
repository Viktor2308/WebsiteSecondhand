package com.example.websitesecondhand.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public final class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super("User is not found");
    }
}
