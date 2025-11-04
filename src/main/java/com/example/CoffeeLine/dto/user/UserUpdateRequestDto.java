package com.example.CoffeeLine.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
@Schema(description = "Used for updating user information")
public class UserUpdateRequestDto {

    @Schema(description = "Must be to found user in DB", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotBlank(message = "is required")
    @Size(min = 36, max = 36, message = "must be a valid UUID")
    String id;

    @Schema(description = "New user name (not required)")
    @Size(min = 2, max = 50, message = "must be between 2 and 50 characters")
    String name;

    @Schema(description = "New user phoneNumber (not required)")
    @Size(min = 10, max = 10, message = "must be exactly 10-digits length")
    @Pattern(
            regexp = "^0\\d{9}",
            message = "must be a 10-digit number starting with 0"
    )
    String phoneNumber;

    @Schema(description = "New user password (not required)")
    @Size(min = 6, message = "must be at least 6 characters")
    String password;
}
