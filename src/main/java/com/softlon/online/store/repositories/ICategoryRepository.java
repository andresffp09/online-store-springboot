package com.softlon.online.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.softlon.online.store.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long>{
    
}
