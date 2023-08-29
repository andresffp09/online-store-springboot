package com.softlon.online.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softlon.online.store.entities.Purchase;

public interface IPurchaseRepository extends JpaRepository<Purchase, Long>{
    
}
