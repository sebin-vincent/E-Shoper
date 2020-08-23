package com.example.Authserver.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Date;

public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

    public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException exception, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("status", exception.getHttpErrorCode());
        jsonGenerator.writeObjectField("message", exception.getMessage());
        jsonGenerator.writeObjectField("timestamp", new Date());
        jsonGenerator.writeObjectField("payload",null);
        jsonGenerator.writeEndObject();


    }
}
