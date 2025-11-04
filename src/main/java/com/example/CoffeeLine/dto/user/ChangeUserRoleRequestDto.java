package com.example.CoffeeLine.dto.user;

import com.example.CoffeeLine.dto.validation.role.ValidRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
@Schema(description = "Used for changing user role (add or remove)")
public class ChangeUserRoleRequestDto {

    @Schema(example = "123e4567-e89b-12d3-a456-426614174000")
    @NotBlank(message = "is required")
    @Size(min = 36, max = 36, message = "must be a valid UUID")
    String id;

    @Schema(description = "Role to be added or removed from a user", example = "ADMIN")
    @NotBlank(message = "is required")
    @ValidRole(message = "Specified role does not exist")
    String role;
}
