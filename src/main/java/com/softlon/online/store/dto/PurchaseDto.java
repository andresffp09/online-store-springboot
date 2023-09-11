package com.softlon.online.store.dto;

import java.time.LocalDateTime;
import java.util.List;


public class PurchaseDto {
    private Long Id;
    private Double discount;
    private double totalPrice;
    private LocalDateTime date;
    private ClientDto client;
    private List<ProductDto> products;
    
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public Double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public List<ProductDto> getProducts() {
        return products;
    }
    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
    public ClientDto getClient() {
        return client;
    }
    public void setClient(ClientDto client) {
        this.client = client;
    }    
}
