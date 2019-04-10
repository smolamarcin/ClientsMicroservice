package com.smola.Clients.domain.clients;

import com.smola.Clients.domain.clients.dto.AddressDto;
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
    ResponseEntity<Client> createClient(@RequestBody ClientDto clientDto) {
        return new ResponseEntity<>(clientService.createClient(clientDto), HttpStatus.CREATED);
    }

    @PutMapping("/clients/{clientEmail}")
    ResponseEntity<Client> addNewAddress(@PathVariable String clientEmail, @RequestBody AddressDto address) {
        return new ResponseEntity<>(clientService.addAddressToClient(address, clientEmail), HttpStatus.OK);
    }
}
