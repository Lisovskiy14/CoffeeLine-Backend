package com.example.CoffeeLine.dto.user;

import com.example.CoffeeLine.dto.validation.ValidRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class ChangeUserRoleRequestDto {

    @NotBlank(message = "is required")
    @Size(min = 36, max = 36, message = "must be a valid UUID")
    String id;

    @NotBlank(message = "is required")
    @ValidRole(message = "Specified role does not exist")
    String role;
}
