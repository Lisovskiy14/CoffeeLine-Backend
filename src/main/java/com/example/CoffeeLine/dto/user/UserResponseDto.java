package com.example.CoffeeLine.dto.user;

import lombok.Value;

@Value
public class UserResponseDto {
    String id;
    String name;
    String email;
    String phoneNumber;
    String roles;
}
