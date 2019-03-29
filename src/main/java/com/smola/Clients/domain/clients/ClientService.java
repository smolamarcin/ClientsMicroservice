package com.smola.Clients.domain.clients;

import java.util.Collection;

public interface ClientService {
    Collection<Client> getAllClients();

    Client createClient(Client client);

    Client addAddressToClient(Address address, String clientEmail);
}
