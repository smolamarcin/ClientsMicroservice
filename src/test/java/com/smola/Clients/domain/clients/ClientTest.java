package com.smola.Clients.domain.clients;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ClientTest {
    @Test
    public void newClientShouldBeInitializedProperly() {
        // given
        String firstName = "Jan";
        String secondName = "Kowalski";
        String email = "email";
        List<Address> clientAddresses = Arrays.asList(new Address("First Address"), new Address("Second Address"));

        // when
        Client client = Client.ClientBuilder.aClient()
                .withFirstName(firstName)
                .withSecondName(secondName)
                .withAddresses(clientAddresses)
                .withEmail(email)
                .build();

        // then
        assertThat(client.getFirstName()).isEqualTo(firstName);
        assertThat(client.getSecondName()).isEqualTo(secondName);
        assertThat(client.getEmail()).isEqualTo(email);
        assertThat(client.getAddresses()).isEqualTo(clientAddresses);
    }

    @Test
    public void whenAddNewAddress_clientsAddressesShouldBeUpdated() {
        // given
        Client client = Client.ClientBuilder.aClient()
                .withAddresses(Arrays.asList(new Address("First Address")))
                .build();

        // when
        client.addAddress(new Address("Second Address"));

        // then
        assertThat(client.getAddresses()).hasSize(2);
    }


}