package com.softlon.online.store.dto;

import java.util.List;

public class CategoryDto {
    private Long Id;
    private String name;
    private List<ProductDto> productsDto;
    
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<ProductDto> getProductsDto() {
        return productsDto;
    }
    public void setProductsDto(List<ProductDto> productsDto) {
        this.productsDto = productsDto;
    }

    
}
