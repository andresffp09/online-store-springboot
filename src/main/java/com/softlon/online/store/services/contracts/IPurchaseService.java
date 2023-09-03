package com.softlon.online.store.services.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.softlon.online.store.entities.Purchase;

public interface IPurchaseService {
    public ResponseEntity<List<Purchase>> findAll();

    public ResponseEntity<Purchase> create(Purchase purchase);

    public ResponseEntity<Purchase> update(Purchase purchase);

    public ResponseEntity<Boolean> delete(Long id);

    public ResponseEntity<List<Purchase>> findAllByDate(String date);

    public ResponseEntity<List<Purchase>> findByUserId(Long id);

    public ResponseEntity<List<Purchase>> findAllByDateRange(String startDate, String endDate);

    public ResponseEntity<List<Purchase>> findByClientAndBetweenDate(Long id, String startDate, String endDate);
}


