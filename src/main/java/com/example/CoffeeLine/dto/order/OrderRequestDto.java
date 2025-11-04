package com.example.CoffeeLine.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.Set;

@Value
@Schema(description = "Used for requesting to create order")
public class OrderRequestDto {

    @Schema(example = "123e4567-e89b-12d3-a456-426614174000")
    @NotBlank(message = "is required")
    @Size(min = 36, max = 36, message = "must be a valid UUID")
    String userId;

    @Schema(description = "Set of order items")
    @NotNull(message = "cant be missing")
    @Size(min = 1, message = "must be at least 1 order item")
    Set<OrderItemRequestDto> orderItems;
}
