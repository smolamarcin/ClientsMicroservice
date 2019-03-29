package com.smola.Clients.domain.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
class ClientsEndpoint {
    private final ClientService clientService;

    @Autowired
    public ClientsEndpoint(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    ResponseEntity<Collection<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PostMapping("/clientsxd")
    ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.createClient(client));
    }

    @PutMapping("/clients/{clientEmail}")
    void addNewAddress(@PathVariable String clientEmail, @RequestBody Address address) {
        clientService.addAddressToClient(address, clientEmail);
    }
}
