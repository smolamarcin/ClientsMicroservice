package com.smola.Clients.domain.clients;

import com.smola.Clients.domain.clients.dto.ClientDto;

import java.util.Collection;
import java.util.List;

interface ClientService {
    List<ClientDto> getAllClients();

    Client createClient(Client client);

    Client addAddressToClient(Address address, String clientEmail);
}
