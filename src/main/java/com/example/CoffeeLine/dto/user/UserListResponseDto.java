package com.example.CoffeeLine.dto.user;

import lombok.Value;

import java.util.List;

@Value
public class UserListResponseDto {
    List<UserResponseDto> users;
}
