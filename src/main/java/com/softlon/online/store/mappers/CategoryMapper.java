package com.softlon.online.store.mappers;

import com.softlon.online.store.dto.CategoryDto;
import com.softlon.online.store.entities.Category;

public class CategoryMapper {
    public static CategoryDto MapToCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    public static Category MapToCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        return category;
    }
}
