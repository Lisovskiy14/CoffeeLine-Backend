package com.example.CoffeeLine.dto.coffee;

import lombok.Value;

import java.util.List;

@Value
public class CoffeeListResponseDto {
    List<CoffeeResponseDto> coffees;
}
