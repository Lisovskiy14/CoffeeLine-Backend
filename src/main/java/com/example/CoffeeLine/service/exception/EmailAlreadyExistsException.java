package com.example.CoffeeLine.service.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    private static final String USER_WITH_EMAIL_ALREADY_EXISTS = "User with email '%s' already exists";

    public EmailAlreadyExistsException(String email) {
        super(String.format(USER_WITH_EMAIL_ALREADY_EXISTS, email));
    }
}
