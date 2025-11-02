package com.example.CoffeeLine.web.mapper;

import com.example.CoffeeLine.domain.Category;
import com.example.CoffeeLine.dto.category.CategoryRequestDto;
import com.example.CoffeeLine.dto.category.CategoryResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponseDto toCategoryResponseDto(Category category);
    Category toCategory(CategoryRequestDto categoryRequestDto);
}
