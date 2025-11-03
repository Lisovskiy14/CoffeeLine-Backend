package com.example.CoffeeLine.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.Set;

@Value
public class OrderRequestDto {

    @NotBlank(message = "is required")
    @Size(min = 36, max = 36, message = "must be a valid UUID")
    String userId;

    @NotNull(message = "cant be missing")
    @Size(min = 1, message = "must be at least 1 order item")
    Set<OrderItemRequestDto> orderItems;
}
