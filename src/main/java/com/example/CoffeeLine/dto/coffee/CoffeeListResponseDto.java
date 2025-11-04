package com.example.CoffeeLine.dto.coffee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.util.List;

@Value
public class CoffeeListResponseDto {
    @Schema(description = "List of coffees")
    List<CoffeeResponseDto> coffees;
}
