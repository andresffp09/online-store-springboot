package com.softlon.online.store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softlon.online.store.dto.CategoryDto;
import com.softlon.online.store.entities.Category;
import com.softlon.online.store.services.contracts.ICategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return categoryService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody Category category){
        return categoryService.create(category);
    }

    @PutMapping("/update")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody Category category){
        return categoryService.update(category);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteCategory(@RequestParam Long id){
        return categoryService.delete(id);
    }
}
