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

import com.softlon.online.store.dto.PurchaseDto;
import com.softlon.online.store.entities.Purchase;
import com.softlon.online.store.services.contracts.IPurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private IPurchaseService purchaseService;

    @GetMapping("/list")
    public ResponseEntity<List<PurchaseDto>> getAllPurchases(){
        return purchaseService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<PurchaseDto> createPurchase(@RequestBody Purchase purchase){
        return purchaseService.create(purchase);
    }

    @PutMapping("/update")
    public ResponseEntity<PurchaseDto> updatePurchase(@RequestBody Purchase purchase){
        return purchaseService.update(purchase);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deletePurchase(@RequestParam Long id){
        return purchaseService.delete(id);
    }

    @GetMapping("/searchByDate")
    public ResponseEntity<List<PurchaseDto>> findPurchasesByDate(@RequestParam("date") String date){
        return purchaseService.findAllByDate(date);
    }

    @GetMapping("/searchByUserId")
    public ResponseEntity<List<PurchaseDto>> findPurchasesByUserId(@RequestParam Long id){
        return purchaseService.findByUserId(id);
    }

    @GetMapping("/searchByDateRange")
    public ResponseEntity<List<PurchaseDto>> findPurchasesByDateRange(
        @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
        return purchaseService.findAllByDateRange(startDate, endDate);
    }

    @GetMapping("/searchByClientAndDateRange")
    public ResponseEntity<List<PurchaseDto>> findPurchasesByClientAndDateRange( @RequestParam("userId") Long id,
            @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
        return purchaseService.findByClientAndBetweenDate(id, startDate, endDate);
    }


}
