package com.smola.Clients.domain.clients;

import com.smola.Clients.domain.clients.dto.AddressDto;
import com.smola.Clients.domain.clients.dto.ClientDto;

import java.util.Collection;
import java.util.List;

interface ClientService {
    List<ClientDto> getAllClients();

    Client createClient(ClientDto clientDto);

    Client addAddressToClient(AddressDto address, String clientEmail);
}
