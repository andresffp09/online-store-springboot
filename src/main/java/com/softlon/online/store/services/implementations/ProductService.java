package com.softlon.online.store.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softlon.online.store.dto.ProductDto;
import com.softlon.online.store.entities.Product;
import com.softlon.online.store.mappers.ProductMapper;
import com.softlon.online.store.repositories.IProductRepository;
import com.softlon.online.store.services.contracts.IProductService;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ResponseEntity<List<ProductDto>> findAll() {
        try{
            List<Product> products = productRepository.findAll();
            List<ProductDto> productsDto = products.stream().map(p -> ProductMapper.MapToProductDto(p)).collect(Collectors.toList());
            return new ResponseEntity<List<ProductDto>>(productsDto, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<List<ProductDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ProductDto> create(Product product) {
        try{
            Product productSaved = productRepository.save(product);
            ProductDto productDto = ProductMapper.MapToProductDto(productSaved);
            return new ResponseEntity<ProductDto>(productDto, HttpStatus.CREATED);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<ProductDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ProductDto> update(Product product) {
        try{
            Product productUpdated= productRepository.save(product);
            ProductDto productDto = ProductMapper.MapToProductDto(productUpdated);
            return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<ProductDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        try{
            productRepository.deleteById(id);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<List<ProductDto>> productsWithHigherPrice(Double price) {
        try{
            List<Product> products = productRepository.productsWithHigherPrice(price);
            List<ProductDto> productsDto = products.stream().map(p -> ProductMapper.MapToProductDto(p)).collect(Collectors.toList());
            return new ResponseEntity<List<ProductDto>>(productsDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<ProductDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ProductDto> findByNameIgnoreCase(String name) {
        try{
            Product product = productRepository.findByNameIgnoreCase(name);
            ProductDto productDto = ProductMapper.MapToProductDto(product);
            return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ProductDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
