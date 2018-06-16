package com.starwars.models.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.starwars.models.response.ResultResponseModel;

import java.util.List;

@Dao
public interface RepoCharacterDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ResultResponseModel> resultList);

    @Query("SELECT * FROM repoCharacter")
    LiveData<List<ResultResponseModel>> getRepoData();
}
