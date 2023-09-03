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

import com.softlon.online.store.constants.Constants;
import com.softlon.online.store.entities.Product;
import com.softlon.online.store.services.contracts.IProductService;

@RestController
@RequestMapping(Constants.PRODUCT)
public class ProductController {
    
    @Autowired
    private IProductService productService;

    @GetMapping(Constants.GET_ALL)
    public ResponseEntity<List<Product>> getAllProducts(){
        return productService.findAll();
    }

    @PostMapping(Constants.CREATE)
    public ResponseEntity<Product> createProducts(@RequestBody Product product){
        return productService.create(product);
    }

    @PutMapping(Constants.UPDATE)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        return productService.update(product);
    }

    @DeleteMapping(Constants.DELETE)
    public ResponseEntity<Boolean> deleteProduct(@RequestParam Long id){
        return productService.delete(id);
    }

    @GetMapping("/higherPrice")
    public ResponseEntity<List<Product>> filterByHigherPrice(@RequestParam("price") Double price){
        return productService.productsWithHigherPrice(price);
    }

    @GetMapping("/search")
    public ResponseEntity<Product> findByName(@RequestParam("name") String name){
        return productService.findByNameIgnoreCase(name);
    }




}
