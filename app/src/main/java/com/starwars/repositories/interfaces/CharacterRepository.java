package com.starwars.repositories.interfaces;

import android.arch.lifecycle.LiveData;

import com.starwars.models.response.ResultResponseModel;

import java.util.List;

public interface CharacterRepository {

    LiveData<List<ResultResponseModel>> getCharacter(int page);

    void saveRepoList(List<ResultResponseModel> responseModels);

    LiveData<List<ResultResponseModel>> getRepoList();
}
