package com.softlon.online.store.services.implementations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softlon.online.store.entities.Purchase;
import com.softlon.online.store.repositories.IPurchaseRepository;
import com.softlon.online.store.services.contracts.IPurchaseService;

@Service
public class PurchaseService implements IPurchaseService{

    @Autowired
    private IPurchaseRepository purchaseRepository;

    @Override
    public ResponseEntity<List<Purchase>> findAll() {
        try{
            List<Purchase> purchases = purchaseRepository.findAll();
            return new ResponseEntity<List<Purchase>>(purchases, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Purchase>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Purchase> create(Purchase purchase) {
        try{
            Purchase purchaseSaved = purchaseRepository.save(purchase);
            return new ResponseEntity<Purchase>(purchaseSaved, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<Purchase>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Purchase> update(Purchase purchase) {
        try{
            Purchase purchaseUpdated = purchaseRepository.save(purchase);
            return new ResponseEntity<Purchase>(purchaseUpdated, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Purchase>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        try{
            purchaseRepository.deleteById(id);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Purchase>> findAllByDate(String dateRequested) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateRequested, formatter);
            LocalDateTime startDateTime = date.atStartOfDay();
            LocalDateTime endDateTime = date.plusDays(1).atStartOfDay();
            List<Purchase> purchases = purchaseRepository.findByDateBetween(startDateTime, endDateTime);
            return new ResponseEntity<List<Purchase> >(purchases, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<List<Purchase> >(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Purchase>> findByUserId(Long id) {
        try{
            List<Purchase> purchases = purchaseRepository.findAllByClient(id);
            return new ResponseEntity<List<Purchase> >(purchases, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<List<Purchase> >(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Purchase>> findAllByDateRange(String startDate, String endDate) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate LocalDateStart = LocalDate.parse(startDate, formatter);
            LocalDate LocalDateEnd = LocalDate.parse(endDate, formatter);
            LocalDateTime startDateTime = LocalDateStart.atStartOfDay();
            LocalDateTime endDateTime = LocalDateEnd.plusDays(1).atStartOfDay();
            List<Purchase> purchases = purchaseRepository.findByDateBetween(startDateTime, endDateTime);
            return new ResponseEntity<List<Purchase> >(purchases, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<List<Purchase> >(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
}
