package com.softlon.online.store.services.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.softlon.online.store.dto.CategoryDto;
import com.softlon.online.store.entities.Category;

public interface ICategoryService {
    public ResponseEntity<List<CategoryDto>> findAll();

    public ResponseEntity<CategoryDto> create(Category category);

    public ResponseEntity<CategoryDto> update(Category category);

    public ResponseEntity<Boolean> delete(Long id);
}
