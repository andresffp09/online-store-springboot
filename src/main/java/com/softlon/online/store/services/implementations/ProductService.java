package com.softlon.online.store.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softlon.online.store.entities.Product;
import com.softlon.online.store.repositories.IProductRepository;
import com.softlon.online.store.services.contracts.IProductService;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ResponseEntity<List<Product>> findAll() {
        try{
            List<Product> products = productRepository.findAll();
            return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Product>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Product> create(Product product) {
        try{
            Product productSaved = productRepository.save(product);
            return new ResponseEntity<Product>(productSaved, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Product> update(Product product) {
        try{
            Product productUpdated= productRepository.save(product);
            return new ResponseEntity<Product>(productUpdated, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<List<Product>> productsWithHigherPrice(Double price) {
        try{
            List<Product> products = productRepository.productsWithHigherPrice(price);
            return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Product>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Product> findByNameIgnoreCase(String name) {
        try{
            Product product = productRepository.findByNameIgnoreCase(name);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
