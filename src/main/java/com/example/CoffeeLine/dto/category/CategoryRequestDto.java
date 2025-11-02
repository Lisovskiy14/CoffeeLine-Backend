package com.example.CoffeeLine.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class CategoryRequestDto {

    @NotBlank(message = "is required")
    @Size(min = 2, max = 25, message = "must be between 2 and 25 characters")
    String name;
}
