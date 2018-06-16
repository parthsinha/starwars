package com.starwars.services;

import com.starwars.services.implementation.RetrofitCharacterService;

public class ServiceEndpoints {
    private RetrofitCharacterService retrofitCharacterService;

    public RetrofitCharacterService.Endpoint getRetrofitCharacterService() {
        if (retrofitCharacterService == null) {
            retrofitCharacterService = new RetrofitCharacterService();
        }
        return retrofitCharacterService.getEndpoint();
    }
}

