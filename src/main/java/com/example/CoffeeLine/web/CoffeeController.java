package com.example.CoffeeLine.web;

import com.example.CoffeeLine.dto.coffee.CoffeeListResponseDto;
import com.example.CoffeeLine.dto.coffee.CoffeeRequestDto;
import com.example.CoffeeLine.dto.coffee.CoffeeResponseDto;
import com.example.CoffeeLine.dto.coffee.CoffeeUpdateRequestDto;
import com.example.CoffeeLine.service.CoffeeService;
import com.example.CoffeeLine.web.mapper.CoffeeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coffees")
@Tag(name = "Coffee", description = "Coffee API for managing coffee items")
public class CoffeeController {
    private final CoffeeService coffeeService;
    private final CoffeeMapper coffeeMapper;

    @Operation(summary = "Get all coffees")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Returning all coffees",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CoffeeListResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Expired JWT",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<Object> getAllCoffees() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new CoffeeListResponseDto(
                        coffeeService.getAllCoffees().stream()
                                .map(coffeeMapper::toCoffeeResponseDto)
                                .toList()));
    }

    @Operation(summary = "Get coffee by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Coffee found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CoffeeResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Expired JWT",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Coffee not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @GetMapping("/{coffeeId}")
    public ResponseEntity<Object> getCoffeeById(@PathVariable UUID coffeeId) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(coffeeMapper.toCoffeeResponseDto(
                        coffeeService.getCoffeeById(coffeeId)));
    }

    @Operation(summary = "Create new coffee (ADMIN)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Coffee created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CoffeeResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Expired JWT",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category with provided ID not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createCoffee(@Valid @RequestBody CoffeeRequestDto coffeeRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(coffeeMapper.toCoffeeResponseDto(
                        coffeeService.createCoffee(coffeeRequestDto)));
    }

    @Operation(summary = "Update coffee (ADMIN)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Coffee updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CoffeeResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Expired JWT",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Coffee with provided ID not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateCoffee(@Valid @RequestBody CoffeeUpdateRequestDto coffeeUpdateRequestDto) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(coffeeMapper.toCoffeeResponseDto(
                        coffeeService.updateCoffee(coffeeUpdateRequestDto)));
    }

    @Operation(summary = "Delete coffee by ID (ADMIN)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Coffee deleted"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Expired JWT",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @DeleteMapping("/{coffeeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteCoffeeById(@PathVariable UUID coffeeId) {
        coffeeService.deleteCoffeeById(coffeeId);
        return ResponseEntity.noContent().build();
    }
}
