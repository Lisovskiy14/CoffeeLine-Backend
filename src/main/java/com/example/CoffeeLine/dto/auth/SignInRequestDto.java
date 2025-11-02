package com.example.CoffeeLine.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class SignInRequestDto {

    @NotBlank(message = "is required")
    @Size(min = 5, max = 100, message = "must be between 2 and 50 characters")
    @Email(message = "must be a valid email format like user@example.com")
    String email;

    @NotBlank(message = "is required")
    @Size(min = 6, message = "must be at least 6 characters")
    String password;
}
