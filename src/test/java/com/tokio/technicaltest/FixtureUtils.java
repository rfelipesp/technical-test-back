package com.tokio.technicaltest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FixtureUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .setDateFormat(new StdDateFormat().withColonInTimeZone(true))
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);


    public static String readObjectFromFile(final String filePath, final String fileName) throws IOException {
        final var resourceDirectory = Paths.get("src", "test", "resources", filePath, fileName);
        final var content = Files.readString(resourceDirectory);
        final var jsonNode = MAPPER.readValue(content, JsonNode.class);
        return jsonNode.toString();
    }

    public static <T> T readObjectFromFile(final String filePath, final String fileName,
                                           final Class<T> clazz) throws IOException {
        return FixtureUtils.mapJsonToObject(readObjectFromFile(filePath, fileName), clazz);
    }

    static <T> T mapJsonToObject(final String input, final Class<T> clazz) throws JsonProcessingException {
        return MAPPER.readValue(input, clazz);
    }

}
