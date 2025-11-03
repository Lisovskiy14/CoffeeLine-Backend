package com.example.CoffeeLine.dto.order;

import com.example.CoffeeLine.dto.validation.status.ValidOrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UpdateOrderStatusRequestDto {

    @NotBlank(message = "is required")
    @Size(min = 36, max = 36, message = "must be a valid UUID")
    String id;

    @NotBlank(message = "is required")
    @ValidOrderStatus(message = "Specified order status does not exist")
    String status;
}
