package com.example.CoffeeLine.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.util.List;

@Value
public class OrderListResponseDto {
    @Schema(description = "List of orders")
    List<OrderResponseDto> orders;
}
