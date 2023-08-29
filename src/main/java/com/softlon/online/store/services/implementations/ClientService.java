package com.softlon.online.store.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softlon.online.store.entities.Client;
import com.softlon.online.store.repositories.IClientRepository;
import com.softlon.online.store.services.contracts.IClientService;

@Service
public class ClientService implements IClientService{

    @Autowired
    private IClientRepository clientRepository;

    @Override
    public ResponseEntity<List<Client>> findAll() {
        try{
            List<Client> clients = clientRepository.findAll();
            return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<List<Client>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Client> create(Client client) {
        try{
            Client clientSaved = clientRepository.save(client);
            return new ResponseEntity<Client>(clientSaved, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<Client>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Client> update(Client client) {
        try{
            Client clientUpdated = clientRepository.save(client);
            return new ResponseEntity<Client>(clientUpdated, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Client>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        try{
            clientRepository.deleteById(id);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
