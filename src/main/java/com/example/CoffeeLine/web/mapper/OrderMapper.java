package com.example.CoffeeLine.web.mapper;

import com.example.CoffeeLine.domain.Order;
import com.example.CoffeeLine.domain.OrderItem;
import com.example.CoffeeLine.dto.order.OrderItemResponseDto;
import com.example.CoffeeLine.dto.order.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    OrderResponseDto toOrderResponseDto(Order order);
    @Mapping(source = "coffee.id", target = "coffeeId")
    OrderItemResponseDto toOrderItemResponseDto(OrderItem orderItem);
}
