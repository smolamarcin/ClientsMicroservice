package com.smola.Clients.domain.clients;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.ser.Serializers;

public class CustomModule extends Module {

    @Override
    public void setupModule(SetupContext setupContext) {
        setupContext.addSerializers(createSerializers());
        setupContext.addDeserializers(createDeserializers());
    }

    private Serializers createSerializers() {
        SimpleSerializers serializers = new SimpleSerializers();
        serializers.addSerializer(zipCodeSerializer());
        return serializers;
    }

    private JsonSerializer<ZipCode> zipCodeSerializer() {
        return new ValueObjectSerializer<>(ZipCode.class, ZipCode::getZipCode);
    }

    private Deserializers createDeserializers() {
        SimpleDeserializers simpleDeserializers = new SimpleDeserializers();
        simpleDeserializers.addDeserializer(ZipCode.class, zipCodeDeserializer());
        return simpleDeserializers;
    }

    private JsonDeserializer<ZipCode> zipCodeDeserializer() {
        return new ValueObjectDeserializer<>(ZipCode.class, ZipCode::new);
    }

    @Override
    public String getModuleName() {
        return "serializers";
    }

    @Override
    public Version version() {
        return Version.unknownVersion();
    }
}
