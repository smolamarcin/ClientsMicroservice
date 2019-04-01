package com.smola.Clients.domain.clients;

import com.smola.Clients.domain.clients.dto.ClientDto;

import java.util.Arrays;

class ClientsProvider {

    static final Client FIRST_CLIENT = Client.ClientBuilder.aClient()
            .withFirstName("Marcin")
            .withSecondName("Smola")
            .withEmail("marcin@smola.com")
            .withAddresses(Arrays.asList(new Address(new ZipCode("33-150"), "Krakowska", 10, "Wroclaw")
                    , new Address(new ZipCode("33-150"), "Wroclawska", 15, "Wroclaw")))
            .build();
    static final ClientDto FIRST_CLIENT_DTO = ClientConverter.toDto(FIRST_CLIENT);
    static final Client SECOND_CLIENT = Client.ClientBuilder.aClient()
            .withFirstName("Jan")
            .withSecondName("Kowalski")
            .withEmail("jan@kowalski.com")
            .withAddresses(Arrays.asList(new Address(new ZipCode("31-150"),"Dietla",12,"Krakow"),
                    new Address(new ZipCode("33-100"),"Krakowska",10,"Tarnow")))
            .build();
    static final ClientDto SECOND_CLIENT_DTO = ClientConverter.toDto(SECOND_CLIENT);
}
