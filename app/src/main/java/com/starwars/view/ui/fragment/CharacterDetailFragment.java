package com.starwars.view.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starwars.R;
import com.starwars.databinding.CharacterDetailBinding;
import com.starwars.models.response.ResultResponseModel;
import com.starwars.view.base.BaseFragment;

public class CharacterDetailFragment extends BaseFragment {
    public static final String TAG = "CharacterDetailFragment";

    private ResultResponseModel resultResponseModel;

    public static CharacterDetailFragment getInstance(ResultResponseModel resultResponseModel) {
        CharacterDetailFragment fragment = new CharacterDetailFragment();
        fragment.resultResponseModel = resultResponseModel;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        CharacterDetailBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mActivity), R.layout.character_detail, container, false);
        binding.setRepo(resultResponseModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
}
