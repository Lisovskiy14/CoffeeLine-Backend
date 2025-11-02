package com.example.CoffeeLine.dto.validation;

import com.example.CoffeeLine.common.Role;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<ValidRole, String> {

    @Override
    public boolean isValid(String roleName, ConstraintValidatorContext context) {
        if (roleName == null) {
            return false;
        }

        try {
            Role.valueOf(roleName);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
