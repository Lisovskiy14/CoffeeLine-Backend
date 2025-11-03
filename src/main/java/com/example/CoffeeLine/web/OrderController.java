package com.example.CoffeeLine.web;

import com.example.CoffeeLine.common.OrderStatus;
import com.example.CoffeeLine.dto.order.OrderListResponseDto;
import com.example.CoffeeLine.dto.order.OrderRequestDto;
import com.example.CoffeeLine.dto.order.UpdateOrderStatusRequestDto;
import com.example.CoffeeLine.service.OrderService;
import com.example.CoffeeLine.web.mapper.OrderMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<Object> getAllOrders(
            @RequestParam(value = "status", required = false) OrderStatus orderStatus) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new OrderListResponseDto(
                        orderService.getAllOrders(orderStatus).stream()
                                .map(orderMapper::toOrderResponseDto)
                                .toList()));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getOrderById(@PathVariable UUID orderId) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderMapper.toOrderResponseDto(
                        orderService.getOrderById(orderId)));
    }

    @PostMapping
    public ResponseEntity<Object> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderMapper.toOrderResponseDto(
                        orderService.createOrder(orderRequestDto)));
    }

    @PutMapping
    public ResponseEntity<Object> updateOrderStatus(
            @Valid @RequestBody UpdateOrderStatusRequestDto updateOrderStatusRequestDto) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderMapper.toOrderResponseDto(
                        orderService.updateOrderStatus(updateOrderStatusRequestDto)));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Object> deleteOrder(@PathVariable UUID orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.noContent().build();
    }

}
