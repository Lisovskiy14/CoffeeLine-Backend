package com.example.CoffeeLine.dto.order;

import com.example.CoffeeLine.dto.validation.status.ValidOrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
@Schema(description = "Used for updating order status")
public class UpdateOrderStatusRequestDto {

    @Schema(example = "123e4567-e89b-12d3-a456-426614174000")
    @NotBlank(message = "is required")
    @Size(min = 36, max = 36, message = "must be a valid UUID")
    String id;

    @Schema(description = "New existing order status", example = "COMPLETED")
    @NotBlank(message = "is required")
    @ValidOrderStatus(message = "Specified order status does not exist")
    String status;
}
