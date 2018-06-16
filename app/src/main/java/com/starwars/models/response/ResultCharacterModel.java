
package com.starwars.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultCharacterModel {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("next")
    private String mNext;
    @SerializedName("previous")
    private Object mPrevious;
    @SerializedName("results")
    private List<ResultResponseModel> mResults;

    public List<ResultResponseModel> getResults() {
        return mResults;
    }

    public void setResults(List<ResultResponseModel> results) {
        mResults = results;
    }

}
