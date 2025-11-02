package com.example.CoffeeLine.service;

import com.example.CoffeeLine.domain.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(UUID id);
    Category createCategory(Category category);
    void deleteCategoryById(UUID id);
}
