package com.softlon.online.store.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.softlon.online.store.entities.Purchase;

public interface IPurchaseRepository extends JpaRepository<Purchase, Long>{


    @Query(value = "SELECT p.* FROM purchases p WHERE client_id = :id", nativeQuery = true)
    public List<Purchase> findAllByClient(Long id);

    public List<Purchase> findByDateBetween(
        LocalDateTime startDateTime,
        LocalDateTime endDateTime);
}
