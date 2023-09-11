package com.softlon.online.store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softlon.online.store.dto.ClientDto;
import com.softlon.online.store.entities.Client;
import com.softlon.online.store.services.contracts.IClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
    
    @Autowired
    private IClientService clientService;

    @GetMapping("/list")
    public ResponseEntity<List<ClientDto>> getAllClients(){
        return clientService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<ClientDto> createClient(@RequestBody Client client){
        return clientService.create(client);
    }

    @PutMapping("/update")
    public ResponseEntity<ClientDto> updateClient(@RequestBody Client client){
        return clientService.update(client);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteClient(@RequestParam Long id){
        return clientService.delete(id);
    }


}
