package com.smola.Clients.domain.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/clients")
class ClientsEndpoint {
    private final ClientService clientService;

    @Autowired
    public ClientsEndpoint(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PostMapping
    ResponseEntity<Client> createClient(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.createClient(client), HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<Client> addNewAddress(@RequestParam(name = "clientEmail") String clientEmail, @RequestBody Address address) {
        return new ResponseEntity<>(clientService.addAddressToClient(address, clientEmail), HttpStatus.OK);
    }
}
