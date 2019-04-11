package com.smola.Clients.domain.clients;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Optional;

class AddressDeserializer extends StdDeserializer<Address> {
    protected AddressDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Address deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode rootNode = jp.getCodec().readTree(jp);

        String streetName = getNode(rootNode, "streetName")
                .filter(node -> node.isTextual())
                .map(node -> node.textValue())
                .orElseThrow(() -> new JsonMappingException(jp, "Missing textual 'streetName' property"));
        Integer houseNumber = getNode(rootNode, "houseNumber")
                .filter(node -> node.isInt())
                .map(node -> node.intValue())
                .orElseThrow(() -> new JsonMappingException(jp, "Missing int 'houseNumber' property"));
        String city = getNode(rootNode, "city")
                .filter(node -> node.isTextual())
                .map(node -> node.textValue())
                .orElseThrow(() -> new JsonMappingException(jp, "Missing textual 'city' property"));

        JsonNode zipCodeNode = getNode(rootNode, "zipCode")
                .orElseThrow(() -> new JsonMappingException(jp, "Missing 'zipCode' property"));
        ZipCode zipCode = zipCodeNode.traverse(jp.getCodec()).readValueAs(ZipCode.class);

        return new Address(zipCode, streetName, houseNumber, city);
    }

    private Optional<JsonNode> getNode(JsonNode node, String name) {
        return Optional.ofNullable(node.get(name));
    }
}
