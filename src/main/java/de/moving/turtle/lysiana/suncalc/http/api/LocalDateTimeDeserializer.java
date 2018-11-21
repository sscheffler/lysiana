package de.moving.turtle.lysiana.suncalc.http.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    protected LocalDateTimeDeserializer() {
        super(LocalDateTimeDeserializer.class);
    }

    @Override
    public LocalDateTime deserialize(
            final JsonParser parser,
            final DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return LocalDateTime.parse(parser.readValueAs(String.class), ISO_OFFSET_DATE_TIME);
    }
}
