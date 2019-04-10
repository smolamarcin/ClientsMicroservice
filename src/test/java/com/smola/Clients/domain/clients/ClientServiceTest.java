package com.smola.Clients.domain.clients;

import com.smola.Clients.domain.clients.dto.ClientDto;
import com.smola.Clients.exceptions.ClientAlreadyExistsException;
import com.smola.Clients.exceptions.ClientNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static com.smola.Clients.domain.clients.ClientsProvider.*;
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
        when(clientRepository.findAll()).thenReturn(Arrays.asList(FIRST_CLIENT, SECOND_CLIENT));

        Collection<ClientDto> allClients = clientService.getAllClients();
        assertThat(allClients.size()).isEqualTo(2);
        assertThat(allClients).contains(FIRST_CLIENT_DTO, SECOND_CLIENT_DTO);
    }

    @Test
    public void shouldThrowException_whenTryToUpdateNonExistingClient() {
        String nonExistingClientEmail = "asdasd@gmail.com";
        when(clientRepository.findByEmail(nonExistingClientEmail)).thenReturn(Optional.empty());

        assertThatExceptionOfType(ClientNotFoundException.class).isThrownBy(() -> clientService.addAddressToClient(AddressConverter.toDto(new Address("Krakow")), nonExistingClientEmail));
    }

    @Test
    public void shouldCreateNewClient() {
        when(clientRepository.findByEmail(FIRST_CLIENT.getEmail())).thenReturn(Optional.empty());
        clientService.createClient(FIRST_CLIENT_DTO);

        verify(clientRepository).save(any());
    }

    @Test
    public void shouldAddNewAddressToClient() {
        Address addressToAdd = new Address("asdad");

        String firstClientAddress = "marcin@smola.com";
        when(clientRepository.findByEmail(firstClientAddress)).thenReturn(Optional.of(FIRST_CLIENT));


        Client updatedClient = clientService.addAddressToClient(AddressConverter.toDto(addressToAdd), firstClientAddress);

        assertThat(updatedClient.getAddresses().size()).isEqualTo(3);
        assertThat(updatedClient.getAddresses()).contains(addressToAdd);

        verify(clientRepository).save(updatedClient);
    }

    @Test
    public void shouldThrowException_whenUserAlreadyExists() {
        when(clientRepository.findByEmail(FIRST_CLIENT.getEmail())).thenReturn(Optional.of(FIRST_CLIENT));

        assertThatExceptionOfType(ClientAlreadyExistsException.class).isThrownBy(() -> clientService.createClient(FIRST_CLIENT_DTO));
    }
}