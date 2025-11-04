package com.example.CoffeeLine.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.util.List;

@Value
public class CategoryListResponseDto {
    @Schema(description = "List of categories")
    List<CategoryResponseDto> categories;
}
