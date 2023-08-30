package com.softlon.online.store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.softlon.online.store.entities.Product;

public interface IProductRepository extends JpaRepository<Product, Long>{
    
    @Query(value = "SELECT p.* FROM products p WHERE price > :price", nativeQuery = true)
    public List<Product> productsWithHigherPrice(@Param("price") Double price);

    public Product findByNameIgnoreCase(String name);


}
