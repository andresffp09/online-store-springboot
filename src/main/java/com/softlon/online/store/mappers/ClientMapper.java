package com.softlon.online.store.mappers;

import com.softlon.online.store.dto.ClientDto;
import com.softlon.online.store.entities.Client;

public class ClientMapper {

    public static ClientDto MapToClientDto(Client client){
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setEmail(client.getEmail());
        return clientDto;
    }

    public static Client MapToClient(ClientDto clientDto){
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setPassword(clientDto.getPassword());
        return client;
    }
}
