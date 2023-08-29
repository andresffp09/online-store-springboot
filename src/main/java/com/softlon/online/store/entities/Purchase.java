package com.softlon.online.store.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Double discount;
    private Double totalPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ManyToMany
    @JoinTable(name = "purchases_products", 
        joinColumns = {@JoinColumn(name = "purchases_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> productsPurchased = new ArrayList<>();

    public Long getId() {
        return Id;
    }

    public List<Product> getProductsPurchased() {
        return productsPurchased;
    }

    public Double getDiscount() {
        return discount;
    }

    public Client getClient() {
        return client;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    


}
