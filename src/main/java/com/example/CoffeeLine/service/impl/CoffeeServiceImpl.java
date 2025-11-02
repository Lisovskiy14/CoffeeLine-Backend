package com.example.CoffeeLine.service.impl;

import com.example.CoffeeLine.domain.Category;
import com.example.CoffeeLine.domain.Coffee;
import com.example.CoffeeLine.dto.coffee.CoffeeRequestDto;
import com.example.CoffeeLine.dto.coffee.CoffeeUpdateRequestDto;
import com.example.CoffeeLine.service.CategoryService;
import com.example.CoffeeLine.service.CoffeeService;
import com.example.CoffeeLine.service.exception.CoffeeNotFoundException;
import com.example.CoffeeLine.service.repository.CategoryRepository;
import com.example.CoffeeLine.service.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CoffeeServiceImpl implements CoffeeService {
    private final CoffeeRepository coffeeRepository;
    private final CategoryService categoryService;

    @Override
    public List<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    @Override
    public Coffee getCoffeeById(UUID id) {
        Optional<Coffee> coffee = coffeeRepository.findById(id);
        if (coffee.isEmpty()) {
            throw new CoffeeNotFoundException(id.toString());
        }
        return coffee.get();
    }

    @Override
    public Coffee createCoffee(CoffeeRequestDto coffeeRequestDto) {
        Category category = categoryService.getCategoryById(UUID.fromString(coffeeRequestDto.getCategoryId()));

        Coffee coffee = Coffee.builder()
                .name(coffeeRequestDto.getName())
                .description(coffeeRequestDto.getDescription())
                .imageUrl(coffeeRequestDto.getImageUrl())
                .price(coffeeRequestDto.getPrice())
                .category(category)
                .build();

        return coffeeRepository.save(coffee);
    }

    @Override
    public Coffee updateCoffee(CoffeeUpdateRequestDto coffeeUpdateRequestDto) {
        Coffee coffee = getCoffeeById(
                UUID.fromString(coffeeUpdateRequestDto.getId()));
        if (coffeeUpdateRequestDto.getName() != null) {
            coffee.setName(coffeeUpdateRequestDto.getName());
        }
        if (coffeeUpdateRequestDto.getDescription() != null) {
            coffee.setDescription(coffeeUpdateRequestDto.getDescription());
        }
        if (coffeeUpdateRequestDto.getPrice() != null) {
            coffee.setPrice(coffeeUpdateRequestDto.getPrice());
        }
        if (coffeeUpdateRequestDto.getImageUrl() != null) {
            coffee.setImageUrl(coffeeUpdateRequestDto.getImageUrl());
        }
        if (coffeeUpdateRequestDto.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(
                    UUID.fromString(coffeeUpdateRequestDto.getCategoryId()));
            coffee.setCategory(category);
        }
        return coffeeRepository.save(coffee);
    }

    @Override
    public void deleteCoffeeById(UUID id) {
        coffeeRepository.deleteById(id);
    }
}
