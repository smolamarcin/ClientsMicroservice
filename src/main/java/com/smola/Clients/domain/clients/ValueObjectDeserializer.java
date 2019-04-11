package com.smola.Clients.domain.clients;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.function.Function;

class ValueObjectDeserializer<T> extends StdDeserializer<T> {
    private final Function<String, T> constructor;

    public ValueObjectDeserializer(Class<?> vc, Function<String, T> constructor) {
        super(vc);
        this.constructor = constructor;
    }

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String value = node.asText();
        return this.constructor.apply(value);
    }
}
