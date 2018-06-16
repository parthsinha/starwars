package com.starwars.services.converter;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.starwars.services.converter.implementations.IsoDateConverter;
import com.starwars.services.converter.implementations.IsoDateWithoutMsConverter;
import com.starwars.services.types.IsoDate;
import com.starwars.services.types.IsoDateWithoutMs;

import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

public class StarWarsGsonConverter {
    private static final StarWarsGsonConverter ourInstance = new StarWarsGsonConverter();
    private final Converter.Factory gsonConverter;

    public StarWarsGsonConverter() {
        Gson gson = getGson();

        gsonConverter = GsonConverterFactory.create(gson);
    }

    public static StarWarsGsonConverter getInstance() {
        return ourInstance;
    }

    @NonNull
    public Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IsoDate.class, new IsoDateConverter());
        builder.registerTypeAdapter(IsoDateWithoutMs.class, new IsoDateWithoutMsConverter());

        return builder.create();
    }

    public Converter.Factory getGsonConverter() {
        return gsonConverter;
    }
}

