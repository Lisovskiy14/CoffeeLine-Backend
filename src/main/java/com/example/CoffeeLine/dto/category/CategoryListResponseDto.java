package com.example.CoffeeLine.dto.category;

import lombok.Value;

import java.util.List;

@Value
public class CategoryListResponseDto {
    List<CategoryResponseDto> categories;
}
