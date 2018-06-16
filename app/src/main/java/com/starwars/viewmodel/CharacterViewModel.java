package com.starwars.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.starwars.models.response.ResultResponseModel;
import com.starwars.repositories.Repositories;

import java.util.List;

public class CharacterViewModel extends AndroidViewModel {

    public final MutableLiveData<Boolean> isRepoAvailable = new MutableLiveData<>();

    public CharacterViewModel(@NonNull Application application) {
        super(application);
    }

    //To get Data from Database
    public LiveData<List<ResultResponseModel>> getResult() {
        return Repositories.getInstance().getCharacterRepository().getRepoList();
    }

    //To get Data from Server
    public LiveData<List<ResultResponseModel>> fetchFromServer(int page) {
        return Repositories.getInstance().getCharacterRepository().getCharacter(page);
    }

    //To save data to Database
    public void saveToDB(List<ResultResponseModel> list) {
        Repositories.getInstance().getCharacterRepository().saveRepoList(list);
    }
}
