package com.softlon.online.store.dto;

import java.util.List;

public class ClientDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<PurchaseDto> purchasesDto;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<PurchaseDto> getPurchasesDto() {
        return purchasesDto;
    }
    public void setPurchasesDto(List<PurchaseDto> purchasesDto) {
        this.purchasesDto = purchasesDto;
    }

    
        
}
