package com.example.CoffeeLine.service.repository;

import com.example.CoffeeLine.common.OrderStatus;
import com.example.CoffeeLine.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> getOrdersByStatus(OrderStatus orderStatus);
}
