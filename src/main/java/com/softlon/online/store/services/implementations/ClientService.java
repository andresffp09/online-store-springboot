package com.softlon.online.store.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softlon.online.store.dto.ClientDto;
import com.softlon.online.store.entities.Client;
import com.softlon.online.store.mappers.ClientMapper;
import com.softlon.online.store.repositories.IClientRepository;
import com.softlon.online.store.services.contracts.IClientService;

@Service
public class ClientService implements IClientService{

    @Autowired
    private IClientRepository clientRepository;

    @Override
    public ResponseEntity<List<ClientDto>> findAll() {
        try{
            List<Client> clients = clientRepository.findAll();
            List<ClientDto> clientsDto = clients.stream().map(c -> ClientMapper.MapToClientDto(c)).collect(Collectors.toList());
            return new ResponseEntity<List<ClientDto>>(clientsDto, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<List<ClientDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ClientDto> create(Client client) {
        try{
            Client clientSaved = clientRepository.save(client);
            ClientDto clientDto = ClientMapper.MapToClientDto(clientSaved);
            return new ResponseEntity<ClientDto>(clientDto, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<ClientDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ClientDto> update(Client client) {
        try{
            Client clientUpdated = clientRepository.save(client);
            ClientDto clientDto = ClientMapper.MapToClientDto(clientUpdated);
            return new ResponseEntity<ClientDto>(clientDto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<ClientDto>(HttpStatus.INTERNAL_SERVER_ERROR);
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
