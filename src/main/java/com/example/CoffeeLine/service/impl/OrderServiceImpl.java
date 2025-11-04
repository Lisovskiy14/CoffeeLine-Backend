package com.example.CoffeeLine.service.impl;

import com.example.CoffeeLine.common.OrderStatus;
import com.example.CoffeeLine.domain.Coffee;
import com.example.CoffeeLine.domain.Order;
import com.example.CoffeeLine.domain.OrderItem;
import com.example.CoffeeLine.domain.User;
import com.example.CoffeeLine.dto.order.OrderRequestDto;
import com.example.CoffeeLine.dto.order.UpdateOrderStatusRequestDto;
import com.example.CoffeeLine.service.CoffeeService;
import com.example.CoffeeLine.service.OrderService;
import com.example.CoffeeLine.service.UserService;
import com.example.CoffeeLine.service.exception.OrderNotFoundException;
import com.example.CoffeeLine.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CoffeeService coffeeService;

    @Override
    public List<Order> getAllOrders(OrderStatus orderStatus) {
        if (orderStatus != null) {
            return orderRepository.getOrdersByStatus(orderStatus);
        }
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUserId(UUID userId) {
        return orderRepository.getOrdersByUserId(userId);
    }

    @Override
    public Order getOrderById(UUID id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new OrderNotFoundException(id.toString());
        }
        return order.get();
    }

    @Override
    public Order createOrder(OrderRequestDto orderRequestDto) {
        User user = userService.getUserById(
                UUID.fromString(orderRequestDto.getUserId()));

        Order order = Order.builder()
                .createdAt(LocalDateTime.now())
                .status(OrderStatus.CREATED)
                .user(user)
                .build();

        Set<OrderItem> orderItems = orderRequestDto.getOrderItems().stream()
                .map(orderItemRequestDto ->
                        OrderItem.builder()
                                .order(order)
                                .coffee(coffeeService.getCoffeeById(
                                        UUID.fromString(orderItemRequestDto.getCoffeeId())))
                                .quantity(orderItemRequestDto.getQuantity())
                                .build())
                .collect(Collectors.toSet());

        order.setItems(orderItems);
        Optional<Double> totalCost = orderItems.stream()
                .map(item -> item.getQuantity() * item.getCoffee().getPrice())
                .reduce(Double::sum);

        totalCost.ifPresent(order::setTotalCost);

        log.info("Creating order with id: {}", order.getId());
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrderStatus(UpdateOrderStatusRequestDto updateOrderStatusRequestDto) {
        Order order = getOrderById(
                UUID.fromString(updateOrderStatusRequestDto.getId()));
        order.setStatus(OrderStatus.valueOf(
                updateOrderStatusRequestDto.getStatus()));

        log.info("Updating status {} for order with id: {}",
                order.getStatus(),
                order.getId());
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(UUID id) {
        log.info("Deleting order with id: {}", id);
        orderRepository.deleteById(id);
    }
}
