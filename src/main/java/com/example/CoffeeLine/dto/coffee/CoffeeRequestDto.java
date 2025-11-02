package com.example.CoffeeLine.dto.coffee;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.UUID;

@Value
public class CoffeeRequestDto {

    @NotBlank(message = "is required")
    @Size(min = 2, max = 50, message = "must be between 2 and 50 characters")
    String name;

    @NotBlank(message = "is required")
    String description;

    @NotBlank(message = "is required")
    String imageUrl;

    @DecimalMin(value = "1", message = "must be at least 1")
    double price;

    @NotBlank(message = "is required")
    String categoryId;
}
