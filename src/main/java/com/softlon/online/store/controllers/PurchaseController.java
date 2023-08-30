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

import com.softlon.online.store.entities.Purchase;
import com.softlon.online.store.services.contracts.IPurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private IPurchaseService purchaseService;

    @GetMapping("/list")
    public ResponseEntity<List<Purchase>> getAllPurchases(){
        return purchaseService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase){
        return purchaseService.create(purchase);
    }

    @PutMapping("/update")
    public ResponseEntity<Purchase> updatePurchase(@RequestBody Purchase purchase){
        return purchaseService.update(purchase);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deletePurchase(@RequestParam Long id){
        return purchaseService.delete(id);
    }

    @GetMapping("/searchByDate")
    public ResponseEntity<List<Purchase>> findPurchasesByDate(@RequestParam("date") String date){
        return purchaseService.findAllByDate(date);
    }

    @GetMapping("/searchByUserId")
    public ResponseEntity<List<Purchase>> findPurchasesByUserId(@RequestParam Long id){
        return purchaseService.findByUserId(id);
    }

    @GetMapping("/searchByDateRange")
    public ResponseEntity<List<Purchase>> findPurchasesByDateRange(
        @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
        return purchaseService.findAllByDateRange(startDate, endDate);
    }
}
