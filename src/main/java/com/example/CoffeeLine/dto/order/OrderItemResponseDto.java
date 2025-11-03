package com.example.CoffeeLine.dto.order;

import lombok.Value;

@Value
public class OrderItemResponseDto {
    String id;
    String coffeeId;
    int quantity;
}
