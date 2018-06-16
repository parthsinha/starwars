package com.starwars.services.converter.implementations;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.starwars.services.types.IsoDate;

import java.lang.reflect.Type;

public class IsoDateConverter implements JsonDeserializer<IsoDate>, JsonSerializer<IsoDate> {
    @Override
    public IsoDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return IsoDate.convertFromString(json.getAsJsonPrimitive().getAsString());
    }

    @Override
    public JsonElement serialize(IsoDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.convertToString());
    }
}
