package com.example.websitesecondhand.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public final class UserUnauthorizedException extends RuntimeException{
    public UserUnauthorizedException() {
        super("User is not authenticated and therefore not authorized to access the resource");
    }
}
