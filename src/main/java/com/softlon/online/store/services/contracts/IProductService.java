package com.softlon.online.store.services.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.softlon.online.store.dto.ProductDto;
import com.softlon.online.store.entities.Product;

public interface IProductService {
    public ResponseEntity<List<ProductDto>> findAll();

    public ResponseEntity<ProductDto> create(Product product);

    public ResponseEntity<ProductDto> update(Product product);

    public ResponseEntity<Boolean> delete(Long id);

    public ResponseEntity<List<ProductDto>> productsWithHigherPrice(Double price);

    public ResponseEntity<ProductDto> findByNameIgnoreCase(String name);  
}
