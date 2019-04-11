package com.smola.Clients.domain.clients;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.function.Function;

class ValueObjectSerializer<T> extends StdSerializer<T> {
    private final Function<T, String> valueExtractor;

    public ValueObjectSerializer(Class<T> t, Function<T, String> valueExtractor) {
        super(t);
        this.valueExtractor = valueExtractor;
    }

    @Override
    public void serialize(T value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(valueExtractor.apply(value));
    }
}
