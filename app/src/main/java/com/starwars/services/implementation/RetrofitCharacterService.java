package com.starwars.services.implementation;

import com.starwars.models.response.ResultCharacterModel;
import com.starwars.services.Services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitCharacterService {
    private RetrofitCharacterService.Endpoint endpoint;

    public RetrofitCharacterService() {
        this.endpoint = Services.getInstance().getSecureRestAdapter().create(RetrofitCharacterService.Endpoint.class);
    }

    public RetrofitCharacterService.Endpoint getEndpoint() {
        return endpoint;
    }

    public interface Endpoint {
        @GET("people/")
        Call<ResultCharacterModel> getCharacterList(@Query("page") int page);
    }
}
