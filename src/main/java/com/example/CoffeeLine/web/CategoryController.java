package com.example.CoffeeLine.web;

import com.example.CoffeeLine.domain.Category;
import com.example.CoffeeLine.dto.category.CategoryListResponseDto;
import com.example.CoffeeLine.dto.category.CategoryRequestDto;
import com.example.CoffeeLine.service.CategoryService;
import com.example.CoffeeLine.web.mapper.CategoryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<Object> getAllCategories() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new CategoryListResponseDto(
                        categoryService.getAllCategories().stream()
                                .map(categoryMapper::toCategoryResponseDto)
                                .toList()));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getCategoryById(@PathVariable UUID categoryId) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(categoryMapper.toCategoryResponseDto(
                        categoryService.getCategoryById(categoryId)));
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toCategory(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(categoryMapper.toCategoryResponseDto(
                        categoryService.createCategory(category)));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable UUID categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }
}
