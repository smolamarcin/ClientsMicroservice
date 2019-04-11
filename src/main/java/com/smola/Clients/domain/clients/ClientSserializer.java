package com.smola.Clients.domain.clients;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

class ClientSserializer extends JsonSerializer<Client> {
    @Override
    public void serialize(Client client, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("firstName", client.getFirstName());
        jsonGenerator.writeStringField("secondName", client.getSecondName());
        jsonGenerator.writeStringField("email", client.getEmail());
        jsonGenerator.writeObjectField("addresses", client.getAddresses());
        jsonGenerator.writeEndObject();
    }
}
