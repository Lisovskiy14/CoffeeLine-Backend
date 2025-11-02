package com.example.CoffeeLine.web;

import com.example.CoffeeLine.dto.coffee.CoffeeListResponseDto;
import com.example.CoffeeLine.dto.coffee.CoffeeRequestDto;
import com.example.CoffeeLine.dto.coffee.CoffeeUpdateRequestDto;
import com.example.CoffeeLine.service.CoffeeService;
import com.example.CoffeeLine.web.mapper.CoffeeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coffees")
public class CoffeeController {
    private final CoffeeService coffeeService;
    private final CoffeeMapper coffeeMapper;

    @GetMapping
    public ResponseEntity<Object> getAllCoffees() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new CoffeeListResponseDto(
                        coffeeService.getAllCoffees().stream()
                                .map(coffeeMapper::toCoffeeResponseDto)
                                .toList()));
    }

    @GetMapping("/{coffeeId}")
    public ResponseEntity<Object> getCoffeeById(@PathVariable UUID coffeeId) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(coffeeMapper.toCoffeeResponseDto(
                        coffeeService.getCoffeeById(coffeeId)));
    }

    @PostMapping
    public ResponseEntity<Object> createCoffee(@Valid @RequestBody CoffeeRequestDto coffeeRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(coffeeMapper.toCoffeeResponseDto(
                        coffeeService.createCoffee(coffeeRequestDto)));
    }

    @PutMapping
    public ResponseEntity<Object> updateCoffee(@Valid @RequestBody CoffeeUpdateRequestDto coffeeUpdateRequestDto) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(coffeeMapper.toCoffeeResponseDto(
                        coffeeService.updateCoffee(coffeeUpdateRequestDto)));
    }

    @DeleteMapping("/{coffeeId}")
    public ResponseEntity<Object> deleteCoffeeById(@PathVariable UUID coffeeId) {
        coffeeService.deleteCoffeeById(coffeeId);
        return ResponseEntity.noContent().build();
    }
}
