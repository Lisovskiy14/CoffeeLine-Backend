package com.example.CoffeeLine.dto.order;

import lombok.Value;

import java.util.Set;

@Value
public class OrderResponseDto {
    String id;
    String createdAt;
    double totalCost;
    String status;
    String userId;
    Set<OrderItemResponseDto> items;
}
