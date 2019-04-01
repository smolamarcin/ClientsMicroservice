package com.smola.Clients.domain.clients;


import com.smola.Clients.domain.clients.dto.ClientDto;
import com.smola.Clients.exceptions.UserAlreadyExistsException;
import com.smola.Clients.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.smola.Clients.exceptions.ExceptionMessages.USER_ALREADY_EXISTS_EXCEPTION_MESSAGE;
import static com.smola.Clients.exceptions.ExceptionMessages.USER_NOT_FOUND_EXCEPTION_MESSAGE;
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
        return clientRepository.findByEmail(client.getEmail()).map(clientRepository::save)
                .orElseThrow(()->new UserAlreadyExistsException(USER_ALREADY_EXISTS_EXCEPTION_MESSAGE));
    }


    @Override
    public Client addAddressToClient(Address address, String clientEmail) {
        Client found = clientRepository.findByEmail(clientEmail)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION_MESSAGE));
        found.addAddress(address);
        return found;
    }
}
