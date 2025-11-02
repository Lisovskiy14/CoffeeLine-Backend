package com.example.CoffeeLine.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UserUpdateRequestDto {

    @NotBlank(message = "is required")
    @Size(min = 36, max = 36, message = "must be a valid UUID")
    String id;

    @Size(min = 2, max = 50, message = "must be between 2 and 50 characters")
    String name;

    @Size(min = 10, max = 10, message = "must be exactly 10-digits length")
    @Pattern(
            regexp = "^0\\d{9}",
            message = "must be a 10-digit number starting with 0"
    )
    String phoneNumber;

    @Size(min = 6, message = "must be at least 6 characters")
    String password;
}
