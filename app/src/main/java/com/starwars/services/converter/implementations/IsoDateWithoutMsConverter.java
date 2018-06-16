package com.starwars.services.converter.implementations;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.starwars.services.types.IsoDateWithoutMs;

import java.lang.reflect.Type;

public class IsoDateWithoutMsConverter implements JsonDeserializer<IsoDateWithoutMs>, JsonSerializer<IsoDateWithoutMs> {
    @Override
    public IsoDateWithoutMs deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return IsoDateWithoutMs.convertFromString(json.getAsJsonPrimitive().getAsString());
    }

    @Override
    public JsonElement serialize(IsoDateWithoutMs src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.convertToString());
    }
}
