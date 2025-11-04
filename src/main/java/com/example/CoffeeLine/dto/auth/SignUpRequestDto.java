package com.example.CoffeeLine.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Value;

@Value
public class SignUpRequestDto {

    @Schema(description = "Name of user", example = "John")
    @NotBlank(message = "is required")
    @Size(min = 2, max = 50, message = "must be between 2 and 50 characters")
    String name;

    @Schema(example = "email@example.com")
    @NotBlank(message = "is required")
    @Size(min = 5, max = 100, message = "must be between 2 and 50 characters")
    @Email(message = "must be a valid email format like user@example.com")
    String email;

    @Schema(example = "0976578259")
    @NotBlank(message = "is required")
    @Size(min = 10, max = 10, message = "must be exactly 10-digits length")
    @Pattern(
            regexp = "^0\\d{9}",
            message = "must be a 10-digit number starting with 0"
    )
    String phoneNumber;

    @NotBlank(message = "is required")
    @Size(min = 6, message = "must be at least 6 characters")
    String password;
}
