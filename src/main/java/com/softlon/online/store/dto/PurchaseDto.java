package com.softlon.online.store.dto;

import java.time.LocalDateTime;
import java.util.List;


public class PurchaseDto {
    private Long Id;
    private Double discount;
    private double totalPrice;
    private LocalDateTime date;
    private ClientDto clientDto;
    private List<ProductDto> productDto;
    
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
    public ClientDto getClientDto() {
        return clientDto;
    }
    public void setClientDto(ClientDto clientDto) {
        this.clientDto = clientDto;
    }
    public List<ProductDto> getProductDto() {
        return productDto;
    }
    public void setProductDto(List<ProductDto> productDto) {
        this.productDto = productDto;
    }
    
    
}
