package com.example.CoffeeLine.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class CategoryResponseDto {
    @Schema(description = "Category ID", example = "a81bc81b-dead-4e5d-abff-90865d1e13b1")
    String id;
    @Schema(description = "Category name")
    String name;
}
