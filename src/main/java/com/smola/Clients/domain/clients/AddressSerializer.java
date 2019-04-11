package com.smola.Clients.domain.clients;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

class AddressSerializer extends StdSerializer<Address> {
    protected AddressSerializer(Class<Address> t) {
        super(t);
    }

    @Override
    public void serialize(Address address, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("zipCode", address.getZipCode().getZipCode());
        jsonGenerator.writeStringField("city", address.getCity());
        jsonGenerator.writeStringField("streetName", address.getStreetName());
        jsonGenerator.writeNumberField("houseNumber", address.getHouseNumber());
        jsonGenerator.writeEndObject();
    }
}
