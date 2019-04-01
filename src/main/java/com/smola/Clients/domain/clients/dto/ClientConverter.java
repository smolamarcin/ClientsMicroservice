package com.smola.Clients.domain.clients.dto;

import com.smola.Clients.domain.clients.Address;
import com.smola.Clients.domain.clients.Client;

import java.util.List;

public class ClientConverter{
    public static ClientDto toDto(Client client) {
        return ClientDto.ClientDtoBuilder.aClientDto()
                .withFirstName(client.getFirstName())
                .withSecondName(client.getSecondName())
                .withEmail(client.getEmail())
                .withAddresses(getAddresses(client.getAddresses()))
                .build();
    }

    private static List<AddressDto> getAddresses(List<Address> client) {
        return AddressConverter.toDto(client);
    }

}
