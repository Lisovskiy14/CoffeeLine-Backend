package com.example.CoffeeLine.dto.coffee;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.UUID;

@Value
public class CoffeeUpdateRequestDto {

    @NotBlank(message = "is required")
    @Size(min = 36, max = 36, message = "must be a valid UUID")
    String id;

    @Size(min = 2, max = 50, message = "must be between 2 and 50 characters")
    String name;

    String description;

    @DecimalMin(value = "1", message = "must be at least 1")
    Double price;

    String imageUrl;

    @Size(min = 36, max = 36, message = "must be a valid UUID")
    String categoryId;
}
