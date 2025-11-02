package com.example.CoffeeLine.service;

import com.example.CoffeeLine.domain.Coffee;
import com.example.CoffeeLine.dto.coffee.CoffeeRequestDto;
import com.example.CoffeeLine.dto.coffee.CoffeeUpdateRequestDto;

import java.util.List;
import java.util.UUID;

public interface CoffeeService {
    List<Coffee> getAllCoffees();
    Coffee getCoffeeById(UUID id);
    Coffee createCoffee(CoffeeRequestDto coffeeRequestDto);
    Coffee updateCoffee(CoffeeUpdateRequestDto coffeeUpdateRequestDto);
    void deleteCoffeeById(UUID id);
}
