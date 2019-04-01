package com.smola.Clients.domain.clients;


import com.smola.Clients.domain.clients.dto.ClientDto;
import com.smola.Clients.exceptions.ClientAlreadyExistsException;
import com.smola.Clients.exceptions.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.smola.Clients.exceptions.ExceptionMessages.CLIENT_ALREADY_EXISTS_EXCEPTION_MESSAGE;
import static com.smola.Clients.exceptions.ExceptionMessages.CLIENT_NOT_FOUND_EXCEPTION_MESSAGE;
import static java.util.stream.Collectors.toList;

@Service
class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDto> getAllClients() {
        List<Client> allClients = clientRepository.findAll();
        return allClients.stream()
                .map(ClientConverter::toDto)
                .collect(toList());
    }

    @Override
    public Client createClient(Client client) {
        if (clientExists(client)) {
            throw new ClientAlreadyExistsException(CLIENT_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        } else {
            return clientRepository.save(client);
        }
    }

    private boolean clientExists(Client client) {
        return clientRepository.findByEmail(client.getEmail()).isPresent();
    }


    @Override
    public Client addAddressToClient(Address address, String clientEmail) {
        Client found = clientRepository.findByEmail(clientEmail)
                .orElseThrow(() -> new ClientNotFoundException(CLIENT_NOT_FOUND_EXCEPTION_MESSAGE));
        found.addAddress(address);
        return found;
    }
}
