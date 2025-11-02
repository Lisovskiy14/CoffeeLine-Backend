package com.example.CoffeeLine.dto.coffee;

import lombok.Value;

@Value
public class CoffeeResponseDto {
    String id;
    String name;
    String description;
    double price;
    String imageUrl;
    String categoryId;
}
