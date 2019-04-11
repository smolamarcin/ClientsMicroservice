package com.smola.Clients.domain.clients;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

class ClientDeserializer extends JsonDeserializer<Client> {
    private final AddressDeserializer addressDeserializer;

    ClientDeserializer(AddressDeserializer addressDeserializer) {
        this.addressDeserializer = addressDeserializer;
    }

    @Override
    public Client deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode rootNode = p.getCodec().readTree(p);
        String firstName = getNode(rootNode, "firstName")
                .filter(node -> node.isTextual())
                .map(node -> node.textValue())
                .orElseThrow(() -> new JsonMappingException(p, "Missing textual 'firstName' property"));


        String secondName = getNode(rootNode, "secondName")
                .filter(node -> node.isTextual())
                .map(node -> node.textValue())
                .orElseThrow(() -> new JsonMappingException(p, "Missing textual 'secondName' property"));

        String email = getNode(rootNode, "email")
                .filter(node -> node.isTextual())
                .map(node -> node.textValue())
                .orElseThrow(() -> new JsonMappingException(p, "Missing textual 'email' property"));

        getNode(rootNode, "addresses")
                .filter(e -> e.isArray())
                .map(e->e.)
                .orElseThrow(() -> new JsonMappingException(p, "Missing textual 'email' property"));


        return Client.ClientBuilder.aClient()
                .withFirstName(firstName)
                .withSecondName(secondName)
                .withEmail(email)
                .build();
    }

    private Optional<JsonNode> getNode(JsonNode node, String name) {
        return Optional.ofNullable(node.get(name));
    }
}
