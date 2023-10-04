package com.example.delivery_div.mapper;

import com.example.delivery_div.dto.CategoryDto;
import com.example.delivery_div.models.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto entityToCategoryDto(Category category);
    List<CategoryDto> entityToCategoryDtoList(List<Category> category);
    Category categoryDtoToEntity(CategoryDto categoryDto);
}
