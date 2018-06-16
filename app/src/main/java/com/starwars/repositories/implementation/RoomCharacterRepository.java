package com.starwars.repositories.implementation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.util.Log;

import com.starwars.application.StarWarsApplication;
import com.starwars.database.AppDatabase;
import com.starwars.models.response.ResultCharacterModel;
import com.starwars.models.response.ResultResponseModel;
import com.starwars.repositories.interfaces.CharacterRepository;
import com.starwars.services.Services;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomCharacterRepository implements CharacterRepository {

    private AppDatabase database;

    public RoomCharacterRepository() {
        database = Room.databaseBuilder(StarWarsApplication.getInstance().getApplicationContext(), AppDatabase.class, AppDatabase.DB_NAME).build();
    }

    @Override
    public LiveData<List<ResultResponseModel>> getCharacter(int page) {

        final MutableLiveData<List<ResultResponseModel>> data = new MutableLiveData<>();

        Call<ResultCharacterModel> call = Services.getEndpoints().getRetrofitCharacterService().getCharacterList(page);
        call.enqueue(new Callback<ResultCharacterModel>() {
            @Override
            public void onResponse(Call<ResultCharacterModel> call, Response<ResultCharacterModel> response) {
                Log.d("TAG", response.code() + "");
                if (response.code() == 200) {
                    data.setValue(response.body().getResults());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResultCharacterModel> call, Throwable t) {
                call.cancel();
                data.setValue(null);
            }
        });

        return data;
    }

    @Override
    public void saveRepoList(List<ResultResponseModel> resultList) {
        Executors.newCachedThreadPool().execute(() -> {
            database.repoCharacterDAO().insertAll(resultList);
        });
    }

    @Override
    public LiveData<List<ResultResponseModel>> getRepoList() {
        return database.repoCharacterDAO().getRepoData();
    }
}
