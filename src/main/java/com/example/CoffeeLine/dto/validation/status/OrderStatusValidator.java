package com.example.CoffeeLine.dto.validation.status;

import com.example.CoffeeLine.common.OrderStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OrderStatusValidator implements ConstraintValidator<ValidOrderStatus, String> {

    @Override
    public boolean isValid(String status, ConstraintValidatorContext context) {
        if (status == null) {
            return false;
        }

        try {
            OrderStatus.valueOf(status);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
