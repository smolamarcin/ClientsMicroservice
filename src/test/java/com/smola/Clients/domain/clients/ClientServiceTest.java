package com.smola.Clients.domain.clients;

import com.smola.Clients.exceptions.ClientAlreadyExistsException;
import com.smola.Clients.exceptions.ClientNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static com.smola.Clients.domain.clients.ClientsProvider.FIRST_CLIENT;
import static com.smola.Clients.domain.clients.ClientsProvider.SECOND_CLIENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class ClientServiceTest {
    private ClientService clientService;
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        clientRepository = mock(ClientRepository.class);
        clientService = new ClientServiceImpl(clientRepository);
    }

    @Test
    public void shouldRetrieveAllClients() {
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        clientService.getAllClients();

        verify(clientRepository).findAll();
    }

    @Test
    public void shouldThrowException_whenTryToUpdateNonExistingClient() {
        String nonExistingClientEmail = "asdasd@gmail.com";
        when(clientRepository.findByEmail(any())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ClientNotFoundException.class).isThrownBy(() -> clientService.addAddressToClient(mock(Address.class), nonExistingClientEmail));
    }

    @Test
    public void shouldCreateNewClient() {
        when(clientRepository.findByEmail(FIRST_CLIENT.getEmail())).thenReturn(Optional.empty());
        clientService.createClient(mock(Client.class));

        verify(clientRepository).save(any());
    }

    @Test
    public void shouldAddNewAddressToClient() {
        Address addressToAdd = new Address("asdad");

        String firstClientAddress = "marcin@smola.com";
        when(clientRepository.findByEmail(firstClientAddress)).thenReturn(Optional.of(FIRST_CLIENT));


        Client updatedClient = clientService.addAddressToClient(addressToAdd, firstClientAddress);

        assertThat(updatedClient.getAddresses().size()).isEqualTo(3);
        assertThat(updatedClient.getAddresses()).contains(addressToAdd);
        verify(clientRepository).save(updatedClient);
    }

    @Test
    public void shouldThrowException_whenUserAlreadyExists() {
        Client client = mock(Client.class);
        when(clientRepository.findByEmail(any())).thenReturn(Optional.of(client));

        assertThatExceptionOfType(ClientAlreadyExistsException.class).isThrownBy(() -> clientService.createClient(client));
    }
}