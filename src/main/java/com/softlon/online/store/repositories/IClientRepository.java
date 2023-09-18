package com.softlon.online.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.softlon.online.store.entities.Client;

public interface IClientRepository extends JpaRepository<Client, Long>{

    @Query(value = "SELECT c.* FROM clients c WHERE email = :email", nativeQuery = true)
    public Client findByEmail(@Param("email") String email);
}
