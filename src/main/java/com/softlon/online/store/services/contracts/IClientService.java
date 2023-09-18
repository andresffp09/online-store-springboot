package com.softlon.online.store.services.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.softlon.online.store.dto.ClientDto;
import com.softlon.online.store.entities.Client;

public interface IClientService {
    public ResponseEntity<List<ClientDto>> findAll();

    public ResponseEntity<ClientDto> create(Client client);

    public ResponseEntity<ClientDto> update(Client client);

    public ResponseEntity<Boolean> delete(Long id);

    public Client findClientByEmail(String email);

}
