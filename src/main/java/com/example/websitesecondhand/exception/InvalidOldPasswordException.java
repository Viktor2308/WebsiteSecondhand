package com.example.websitesecondhand.exception;

public final class InvalidOldPasswordException extends RuntimeException {
    public InvalidOldPasswordException(final String message) {
        super(message);
    }
}
