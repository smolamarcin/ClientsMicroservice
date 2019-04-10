package com.smola.Clients.domain.clients;

import com.smola.Clients.domain.clients.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class ClientsEndpoint {
    private final ClientService clientService;

    @Autowired
    public ClientsEndpoint(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PostMapping("/clients")
    ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client client1 = clientService.createClient(client);
        return new ResponseEntity<>(client1, HttpStatus.CREATED);
    }

    @PutMapping("/clients/{clientEmail}")
    void addNewAddress(@PathVariable String clientEmail, @RequestBody Address address) {
        clientService.addAddressToClient(address, clientEmail);
    }
}
