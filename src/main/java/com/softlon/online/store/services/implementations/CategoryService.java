package com.softlon.online.store.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softlon.online.store.dto.CategoryDto;
import com.softlon.online.store.entities.Category;
import com.softlon.online.store.mappers.CategoryMapper;
import com.softlon.online.store.repositories.ICategoryRepository;
import com.softlon.online.store.services.contracts.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    public ResponseEntity<List<CategoryDto>> findAll(){
        try {
            List<Category> categories = categoryRepository.findAll();
            List<CategoryDto> categoryDtos = categories.stream().map(c -> CategoryMapper.MapToCategoryDto(c)).collect(Collectors.toList());
            return new ResponseEntity<List<CategoryDto>>(categoryDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<CategoryDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<CategoryDto> create(Category category){
        try {
            Category categorySaved = categoryRepository.save(category);
            CategoryDto categoryDto = CategoryMapper.MapToCategoryDto(categorySaved);
            return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<CategoryDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<CategoryDto> update(Category category) {
        try {
            Category categoryUpdate = categoryRepository.save(category);
            CategoryDto categoryDto = CategoryMapper.MapToCategoryDto(categoryUpdate);
            return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<CategoryDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Boolean> delete(Long id){
        try {
            categoryRepository.deleteById(id);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
