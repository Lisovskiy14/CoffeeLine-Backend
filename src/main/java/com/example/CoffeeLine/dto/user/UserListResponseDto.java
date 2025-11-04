package com.example.CoffeeLine.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.util.List;

@Value
public class UserListResponseDto {
    @Schema(description = "List of users")
    List<UserResponseDto> users;
}
