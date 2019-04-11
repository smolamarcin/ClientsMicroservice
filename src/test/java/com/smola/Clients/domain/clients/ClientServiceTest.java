package com.smola.Clients.domain.clients;

import com.smola.Clients.exceptions.ClientAlreadyExistsException;
import com.smola.Clients.exceptions.ClientNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class ClientServiceTest {
    private ClientService clientService;
    private ClientRepository clientRepository;
    private Client sampleClient;

    @BeforeEach
    public void setUp() {
        clientRepository = mock(ClientRepository.class);
        clientService = new ClientServiceImpl(clientRepository);
        sampleClient = mock(Client.class);
    }

    @Test
    public void shouldRetrieveAllClients() {
        clientService.getAllClients();

        verify(clientRepository).findAll();
    }

    @Test
    public void shouldThrowException_whenTryToUpdateNonExistingClient() {
        String nonExistingClientEmail = "asdasd@gmail.com";
        when(clientRepository.findByEmail(nonExistingClientEmail)).thenReturn(Optional.empty());

        assertThatExceptionOfType(ClientNotFoundException.class).isThrownBy(() -> clientService.addAddressToClient(mock(Address.class), nonExistingClientEmail));
    }

    @Test
    public void shouldCreateNewClient_whenUserWithSameEmailDoesNotExists() {
        when(clientRepository.findByEmail("some client email")).thenReturn(Optional.empty());

        clientService.createClient(sampleClient);

        verify(clientRepository).save(sampleClient);
    }

    @Test
    public void shouldAddNewAddressToExistingClient() {
        Address addressToAdd = new Address("sample address");

        when(clientRepository.findByEmail(any())).thenReturn(Optional.of(sampleClient));

        clientService.addAddressToClient(addressToAdd, "some client email");

        verify(sampleClient).addAddress(addressToAdd);
    }

    @Test
    public void addingNewAddressToClient_shouldUpdateClientInDatabase() {
        Address addressToAdd = new Address("asdad");
        String sampleClientEmail = "someemail@gmail.com";
        when(clientRepository.findByEmail(sampleClientEmail)).thenReturn(Optional.of(sampleClient));

        clientService.addAddressToClient(addressToAdd, sampleClientEmail);

        verify(clientRepository).save(sampleClient);
    }

    @Test
    public void shouldThrowException_whenUserAlreadyExists() {
        when(clientRepository.findByEmail(any())).thenReturn(Optional.of(sampleClient));

        assertThatExceptionOfType(ClientAlreadyExistsException.class).isThrownBy(() -> clientService.createClient(sampleClient));
    }
}