package com.smola.Clients.domain.clients;

import java.util.List;

interface ClientService {
    List<Client> getAllClients();

    Client createClient(Client clientDto);

    Client addAddressToClient(Address address, String clientEmail);
}
