package com.example.CoffeeLine.web.mapper;

import com.example.CoffeeLine.domain.Coffee;
import com.example.CoffeeLine.dto.coffee.CoffeeResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    @Mapping(source = "category.id", target = "categoryId")
    CoffeeResponseDto toCoffeeResponseDto(Coffee coffee);
}
