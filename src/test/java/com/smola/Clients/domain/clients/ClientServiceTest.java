package com.smola.Clients.domain.clients;

import com.smola.Clients.domain.exceptions.UserNotFoundException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

    private ClientService clientService;
    private ClientRepository clientRepository;
    private Client FIRST_CLIENT;
    private Client SECOND_CLIENT;

    @BeforeMethod
    public void setUp() {
        FIRST_CLIENT = Client.ClientBuilder.aClient()
                .withFirstName("Marcin")
                .withSecondName("Smola")
                .build();
        FIRST_CLIENT.addAddresses(Arrays.asList(new Address("Krakow"), new Address("Wroclaw")));
        SECOND_CLIENT = Client.ClientBuilder.aClient()
                .withFirstName("Jan")
                .withSecondName("Kowalski")
                .build();
        SECOND_CLIENT.addAddresses(Arrays.asList(new Address("Krakow"), new Address("Wroclaw")));
        clientRepository = mock(ClientRepository.class);
        clientService = new ClientServiceImpl(clientRepository);
    }

    @Test
    public void shouldRetrieveAllClients() {
        when(clientRepository.findAll()).thenReturn(Arrays.asList(FIRST_CLIENT, SECOND_CLIENT));

        assertThat(clientService.getAllClients().size()).isEqualTo(2);
        assertThat(clientService.getAllClients()).contains(FIRST_CLIENT, SECOND_CLIENT);
    }

    @Test(expectedExceptions = UserNotFoundException.class)
    public void shouldThrowException_whenTryToUpdateNonExistingClient() {
        String nonExistingClientEmail = "asdasd@gmail.com";
        when(clientRepository.findByEmail(nonExistingClientEmail)).thenReturn(Optional.empty());

        clientService.addAddressToClient(new Address("Krakow"), nonExistingClientEmail);
    }

    @Test
    public void shouldAddNewAddressToClient() {
        Address addressToAdd = new Address("asdad");

        String firstClientAddress = "marcin@smola.com";

        when(clientRepository.findByEmail(firstClientAddress)).thenReturn(Optional.of(FIRST_CLIENT));


        Client updatedClient = clientService.addAddressToClient(addressToAdd, firstClientAddress);

        assertThat(updatedClient.getAddresses().size()).isEqualTo(3);
        assertThat(updatedClient.getAddresses()).contains(addressToAdd);
    }
}