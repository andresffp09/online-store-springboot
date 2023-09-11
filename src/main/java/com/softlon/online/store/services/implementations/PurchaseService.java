package com.softlon.online.store.services.implementations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softlon.online.store.constants.Constants;
import com.softlon.online.store.dto.PurchaseDto;
import com.softlon.online.store.entities.Product;
import com.softlon.online.store.entities.Purchase;
import com.softlon.online.store.mappers.PurchaseMapper;
import com.softlon.online.store.repositories.IProductRepository;
import com.softlon.online.store.repositories.IPurchaseRepository;
import com.softlon.online.store.services.contracts.IPurchaseService;

@Service
public class PurchaseService implements IPurchaseService{

    @Autowired
    private IPurchaseRepository purchaseRepository;
    @Autowired
    private IProductRepository productRepository;

    @Override
    public ResponseEntity<List<PurchaseDto>> findAll() {
        try{
            List<Purchase> purchases = purchaseRepository.findAll();
            List<PurchaseDto> purchaseDtos = purchases.stream().map(p -> PurchaseMapper.MapToPurchaseDto(p)).collect(Collectors.toList());
            return new ResponseEntity<List<PurchaseDto>>(purchaseDtos, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<List<PurchaseDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<PurchaseDto> create(Purchase purchase) {
        try{
            // Computing sub-total price:
            Double subTotal = 0.0;
            if (purchase.getProducts().isEmpty()){
                return new ResponseEntity<PurchaseDto>(HttpStatus.BAD_REQUEST);
            }
            for (Product product : purchase.getProducts()){
                Long productId= product.getId();
                subTotal += productRepository.findById(productId).get().getPrice();
            }
            // Computing discount:
            Double discount = CalulateDiscount(purchase.getClient().getId());
            purchase.setDiscount(discount);
            // Computing final price:
            Double total = subTotal * (1.0 - discount);
            purchase.setTotalPrice(total);
            // Saving purchase order:
            Purchase purchaseSaved = purchaseRepository.save(purchase);
            PurchaseDto purchaseDto = PurchaseMapper.MapToPurchaseDto(purchaseSaved);
            return new ResponseEntity<PurchaseDto>(purchaseDto, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<PurchaseDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<PurchaseDto> update(Purchase purchase) {
        try{
            Purchase purchaseUpdated = purchaseRepository.save(purchase);
            PurchaseDto purchaseDto = PurchaseMapper.MapToPurchaseDto(purchaseUpdated);
            return new ResponseEntity<PurchaseDto>(purchaseDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<PurchaseDto>(HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<List<PurchaseDto>> findAllByDate(String dateRequested) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateRequested, formatter);
            LocalDateTime startDateTime = date.atStartOfDay();
            LocalDateTime endDateTime = date.plusDays(1).atStartOfDay();
            List<Purchase> purchases = purchaseRepository.findByDateBetween(startDateTime, endDateTime);
            List<PurchaseDto> purchaseDtos = purchases.stream().map(p -> PurchaseMapper.MapToPurchaseDto(p)).collect(Collectors.toList());
            return new ResponseEntity<List<PurchaseDto>>(purchaseDtos, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<List<PurchaseDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<PurchaseDto>> findByUserId(Long id) {
        try{
            List<Purchase> purchases = purchaseRepository.findAllByClient(id);
            List<PurchaseDto> purchaseDtos = purchases.stream().map(p -> PurchaseMapper.MapToPurchaseDto(p)).collect(Collectors.toList());
            return new ResponseEntity<List<PurchaseDto>>(purchaseDtos, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<List<PurchaseDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<PurchaseDto>> findAllByDateRange(String startDate, String endDate) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate LocalDateStart = LocalDate.parse(startDate, formatter);
            LocalDate LocalDateEnd = LocalDate.parse(endDate, formatter);
            LocalDateTime startDateTime = LocalDateStart.atStartOfDay();
            LocalDateTime endDateTime = LocalDateEnd.plusDays(1).atStartOfDay();
            List<Purchase> purchases = purchaseRepository.findByDateBetween(startDateTime, endDateTime);
            List<PurchaseDto> purchaseDtos = purchases.stream().map(p -> PurchaseMapper.MapToPurchaseDto(p)).collect(Collectors.toList());
            return new ResponseEntity<List<PurchaseDto> >(purchaseDtos, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<List<PurchaseDto> >(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<PurchaseDto>> findByClientAndBetweenDate(Long id, String startDate, String endDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate LocalDateStart = LocalDate.parse(startDate, formatter);
            LocalDate LocalDateEnd = LocalDate.parse(endDate, formatter);
            LocalDateTime startDateTime = LocalDateStart.atStartOfDay();
            LocalDateTime endDateTime = LocalDateEnd.plusDays(1).atStartOfDay();
            List<Purchase> purchases = purchaseRepository.findByClientAndBetweenDates(id, startDateTime, endDateTime);
            List<PurchaseDto> purchaseDtos = purchases.stream().map(p -> PurchaseMapper.MapToPurchaseDto(p)).collect(Collectors.toList());
            return new ResponseEntity<List<PurchaseDto>>(purchaseDtos, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<List<PurchaseDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Double CalulateDiscount(Long userId){
        LocalDateTime ThirtyDaysAgo = LocalDateTime.now().minusDays(30);
        LocalDateTime today = LocalDateTime.now();
        List<Purchase> purchases = purchaseRepository.findByClientAndBetweenDates(userId, ThirtyDaysAgo, today);
        if (purchases.size() > 0) {
            for (Purchase purchase : purchases) {
                if (purchase.getTotalPrice() > 1000000){
                    return Constants.DISCOUNT;
                }
            }
        }
        return 0.0;
    }

    
    
}
