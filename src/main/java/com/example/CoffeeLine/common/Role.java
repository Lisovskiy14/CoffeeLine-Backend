package com.example.CoffeeLine.common;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_CASHIER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
