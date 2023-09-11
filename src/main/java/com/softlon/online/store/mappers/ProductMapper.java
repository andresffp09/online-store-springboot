package com.softlon.online.store.mappers;

import com.softlon.online.store.dto.ProductDto;
import com.softlon.online.store.entities.Product;

public class ProductMapper {

    public static ProductDto MapToProductDto(Product product){

        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPhotoUrl(product.getPhotoUrl());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.getCategoryDto().setId(product.getCategory().getId());
        productDto.getCategoryDto().setName(product.getCategory().getName());
        productDto.setStock(product.getStock());

        return productDto;
    }

    public static Product MapToProduct(ProductDto productDto){
        
        Product product = new Product();

        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPhotoUrl(productDto.getPhotoUrl());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.getCategory().setId(productDto.getCategoryDto().getId());
        product.setStock(productDto.getStock());

        return product;
    }

}
