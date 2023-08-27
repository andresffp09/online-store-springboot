package com.softlon.online.store.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softlon.online.store.entities.Category;
import com.softlon.online.store.repositories.ICategoryRepository;
import com.softlon.online.store.services.contracts.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    public ResponseEntity<List<Category>> findAll(){
        try {
            List<Category> categories = categoryRepository.findAll();
            return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Category>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Category> create(Category category){
        try {
            Category categorySaved = categoryRepository.save(category);
            return new ResponseEntity<Category>(categorySaved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
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
