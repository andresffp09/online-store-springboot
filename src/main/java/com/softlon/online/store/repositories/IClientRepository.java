package com.softlon.online.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softlon.online.store.entities.Client;

public interface IClientRepository extends JpaRepository<Client, Long>{
    
}
