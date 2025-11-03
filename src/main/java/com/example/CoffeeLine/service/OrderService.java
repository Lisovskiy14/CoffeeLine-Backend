package com.example.CoffeeLine.service;

import com.example.CoffeeLine.common.OrderStatus;
import com.example.CoffeeLine.domain.Order;
import com.example.CoffeeLine.dto.order.OrderRequestDto;
import com.example.CoffeeLine.dto.order.UpdateOrderStatusRequestDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<Order> getAllOrders(OrderStatus orderStatus);
    Order getOrderById(UUID id);
    Order createOrder(OrderRequestDto orderRequestDto);
    Order updateOrderStatus(UpdateOrderStatusRequestDto updateOrderStatusRequestDto);
    void deleteOrderById(UUID id);
}
