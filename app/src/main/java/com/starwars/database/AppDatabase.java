package com.starwars.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.starwars.models.database.dao.RepoCharacterDAO;
import com.starwars.models.response.ResultResponseModel;

@Database(entities = {ResultResponseModel.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "starWarsDB";
    private static AppDatabase dbInstance;
    private static final Object LOCK = new Object();

    public static AppDatabase getInstance(Context context) {
        if (dbInstance == null) {
            synchronized (LOCK) {
                dbInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppDatabase.DB_NAME).build();
            }
        }
        return dbInstance;
    }

    public void clear() {
        dbInstance.clearAllTables();
    }

    public abstract RepoCharacterDAO repoCharacterDAO();
}
