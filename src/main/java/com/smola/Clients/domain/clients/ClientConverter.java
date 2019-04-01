package com.smola.Clients.domain.clients;

import com.smola.Clients.domain.clients.dto.AddressDto;
import com.smola.Clients.domain.clients.dto.ClientDto;

import java.util.List;

class ClientConverter{
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
