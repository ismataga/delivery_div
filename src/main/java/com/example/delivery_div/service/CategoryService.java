package com.example.delivery_div.service;

import com.example.delivery_div.dto.CategoryDto;
import com.example.delivery_div.exception.UserNotFoundException;
import com.example.delivery_div.mapper.CategoryMapper;
import com.example.delivery_div.models.Category;
import com.example.delivery_div.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    public List<CategoryDto> getAllCategory() {
        log.info("getAllCategory().start");
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDto = categoryMapper.entityToCategoryDtoList(categories);
        log.info("getAllCategory().end");
        return categoryDto;

    }

    public CategoryDto getCategoryById(Long id) {
        log.info("getCategoryById().start " + id);
        Category category = categoryRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        CategoryDto categoryDto = categoryMapper.entityToCategoryDto(category);
        log.info("getCategoryById().end " + id);
        return categoryDto;
    }

    public void updateCategoryById(Long id, CategoryDto categoryDto) {
        log.info("updateCategoryById().start " + id);
        Category category = categoryRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        if (categoryDto != null) {
            category.setName(categoryDto.getName());
            categoryRepository.save(category);
        }
        log.info("updateCategoryById().end " + id);
    }

    public void deleteCategoryById(Long id) {
        log.info("deleteFoodById().start " + id);
        categoryRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        categoryRepository.deleteById(id);
        log.info("deleteFoodById().end " + id);
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        log.info("createCategory().start");
        Category category = categoryMapper.categoryDtoToEntity(categoryDto);
        category = categoryRepository.save(category);
        log.info("createCategory().end");
        return categoryMapper.entityToCategoryDto(category);
    }
}
