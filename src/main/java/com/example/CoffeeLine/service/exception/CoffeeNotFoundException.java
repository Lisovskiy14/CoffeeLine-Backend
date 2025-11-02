package com.example.CoffeeLine.service.exception;

public class CoffeeNotFoundException extends ResourceNotFoundException {
    private static final String COFFEE_WITH_ID_NOT_FOUND = "Coffee with id '%s' not found";

    public CoffeeNotFoundException(String id) {
        super(String.format(COFFEE_WITH_ID_NOT_FOUND, id));
    }
}
