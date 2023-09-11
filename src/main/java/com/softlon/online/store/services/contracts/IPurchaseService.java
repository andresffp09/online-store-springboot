package com.softlon.online.store.services.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.softlon.online.store.dto.PurchaseDto;
import com.softlon.online.store.entities.Purchase;

public interface IPurchaseService {
    public ResponseEntity<List<PurchaseDto>> findAll();

    public ResponseEntity<PurchaseDto> create(Purchase purchase);

    public ResponseEntity<PurchaseDto> update(Purchase purchase);

    public ResponseEntity<Boolean> delete(Long id);

    public ResponseEntity<List<PurchaseDto>> findAllByDate(String date);

    public ResponseEntity<List<PurchaseDto>> findByUserId(Long id);

    public ResponseEntity<List<PurchaseDto>> findAllByDateRange(String startDate, String endDate);

    public ResponseEntity<List<PurchaseDto>> findByClientAndBetweenDate(Long id, String startDate, String endDate);
}


