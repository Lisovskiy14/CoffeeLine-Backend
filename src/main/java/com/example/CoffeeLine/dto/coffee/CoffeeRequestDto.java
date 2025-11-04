package com.example.CoffeeLine.dto.coffee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;


@Value
public class CoffeeRequestDto {

    @Schema(description = "Name of coffee", example = "Cappuccino")
    @NotBlank(message = "is required")
    @Size(min = 2, max = 50, message = "must be between 2 and 50 characters")
    String name;

    @Schema(description = "Description of coffee")
    @NotBlank(message = "is required")
    String description;

    @Schema(description = "Image URL of coffee")
    @NotBlank(message = "is required")
    String imageUrl;

    @Schema(description = "Price of coffee")
    @DecimalMin(value = "1", message = "must be at least 1")
    double price;

    @Schema(description = "Category ID of coffee")
    @NotBlank(message = "is required")
    String categoryId;
}
