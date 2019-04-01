package com.smola.Clients.domain.clients;

import com.smola.Clients.domain.clients.dto.ClientConverter;
import com.smola.Clients.domain.clients.dto.ClientDto;

import java.util.Arrays;

class ClientsProvider {

    static final Client FIRST_CLIENT = Client.ClientBuilder.aClient()
            .withFirstName("Marcin")
            .withSecondName("Smola")
            .withEmail("marcin@smola.com")
            .withAddresses(Arrays.asList(new Address("Krakow"), new Address("Wroclaw")))
            .build();
    static final ClientDto FIRST_CLIENT_DTO = ClientConverter.toDto(FIRST_CLIENT);
    static final Client SECOND_CLIENT = Client.ClientBuilder.aClient()
            .withFirstName("Jan")
            .withSecondName("Kowalski")
            .withEmail("jan@kowalski.com")
            .withAddresses(Arrays.asList(new Address("Krakow"), new Address("Wroclaw")))
            .build();
    static final ClientDto SECOND_CLIENT_DTO = ClientConverter.toDto(SECOND_CLIENT);
}
