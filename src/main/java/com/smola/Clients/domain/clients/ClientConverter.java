package com.smola.Clients.domain.clients;

import com.smola.Clients.domain.clients.dto.AddressDto;
import com.smola.Clients.domain.clients.dto.ClientDto;

import java.util.List;

class ClientConverter {
    public static ClientDto toDto(Client client) {
        return ClientDto.ClientDtoBuilder.aClientDto()
                .withFirstName(client.getFirstName())
                .withSecondName(client.getSecondName())
                .withEmail(client.getEmail())
                .withAddresses(ClientConverter.transferToDtoAddresses(client.getAddresses()))
                .build();
    }

    private static List<AddressDto> transferToDtoAddresses(List<Address> clientAddresses) {
        return AddressConverter.toDto(clientAddresses);
    }

    private static List<Address> transferToEntityAddresses(List<AddressDto> clientDtoAddresses) {
        return AddressConverter.toEntity(clientDtoAddresses);
    }

    public static Client toEntity(ClientDto clientDto) {
        return Client.ClientBuilder.aClient()
                .withFirstName(clientDto.getFirstName())
                .withSecondName(clientDto.getSecondName())
                .withEmail(clientDto.getEmail())
                .withAddresses(transferToEntityAddresses(clientDto.getAddresses()))
                .build();
    }
}
