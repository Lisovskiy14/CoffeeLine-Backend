package com.example.CoffeeLine.service.impl;

import com.example.CoffeeLine.domain.Category;
import com.example.CoffeeLine.service.CategoryService;
import com.example.CoffeeLine.service.exception.CategoryNotFoundException;
import com.example.CoffeeLine.service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new CategoryNotFoundException(id.toString());
        }
        return category.get();
    }

    @Override
    public Category createCategory(Category category) {
        log.info("Creating new category with id: {}", category.getId());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(UUID id) {
        log.info("Deleting category with id: {}", id);
        categoryRepository.deleteById(id);
    }
}
