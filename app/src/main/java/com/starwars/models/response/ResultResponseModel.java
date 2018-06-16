
package com.starwars.models.response;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.starwars.models.database.ListTypeConverters;

import java.util.List;

@Entity(tableName = "repoCharacter")
public class ResultResponseModel {

    @PrimaryKey(autoGenerate = true)

    public long id;

    @SerializedName("birth_year")
    public String mBirthYear;

    @SerializedName("created")
    public String mCreated;

    @SerializedName("edited")
    public String mEdited;

    @SerializedName("eye_color")
    public String mEyeColor;

    @SerializedName("films")
    @TypeConverters(ListTypeConverters.class)
    public List<String> mFilms;

    @SerializedName("gender")
    public String mGender;

    @SerializedName("hair_color")
    public String mHairColor;

    @SerializedName("height")
    public String mHeight;

    @SerializedName("homeworld")
    public String mHomeworld;

    @SerializedName("mass")
    public String mMass;

    @SerializedName("name")
    public String mName;

    @SerializedName("skin_color")
    public String mSkinColor;

    @SerializedName("species")
    @TypeConverters(ListTypeConverters.class)
    public List<String> mSpecies;

    @SerializedName("starships")
    @TypeConverters(ListTypeConverters.class)
    public List<String> mStarships;

    @SerializedName("url")
    public String mUrl;

    @SerializedName("vehicles")
    @TypeConverters(ListTypeConverters.class)
    public List<String> mVehicles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
