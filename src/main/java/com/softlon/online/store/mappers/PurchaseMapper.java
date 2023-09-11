package com.softlon.online.store.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.softlon.online.store.dto.ProductDto;
import com.softlon.online.store.dto.PurchaseDto;
import com.softlon.online.store.entities.Product;
import com.softlon.online.store.entities.Purchase;

public class PurchaseMapper {
    public static PurchaseDto MapToPurchaseDto(Purchase purchase) {
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setId(purchase.getId());
        purchaseDto.setDiscount(purchase.getDiscount());
        purchaseDto.setTotalPrice(purchase.getTotalPrice());
        purchaseDto.setDate(purchase.getDate());
        purchaseDto.getClientDto().setId(purchase.getClient().getId());
        purchaseDto.getClientDto().setName(purchase.getClient().getName());
        purchaseDto.getClientDto().setEmail(purchase.getClient().getEmail());

        // Map the list of products
        if (purchase.getProducts() != null) {
            List<ProductDto> productDtos = purchase.getProducts().stream()
                .map(ProductMapper::MapToProductDto)
                .collect(Collectors.toList());
            purchaseDto.setProductDto(productDtos);
        }
        return purchaseDto;
    }

    public static Purchase MapToPurchase(PurchaseDto purchaseDto) {
        Purchase purchase = new Purchase();
        purchase.getClient().setId(purchase.getClient().getId());
        List<Product> products = new ArrayList<>();
        purchaseDto.getProductDto().forEach(p -> products.add(ProductMapper.MapToProduct(p)));
        purchase.setProducts(products);
        return purchase;
    }


}
