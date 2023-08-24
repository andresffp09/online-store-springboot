package com.softlon.online.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.softlon.online.store.entities.Product;

public interface IProductRepository extends JpaRepository<Product, Long>{
    
}
