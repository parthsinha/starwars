package com.starwars.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.starwars.R;
import com.starwars.application.StarWarsApplication;
import com.starwars.databinding.ActivityMainBinding;
import com.starwars.models.response.ResultResponseModel;
import com.starwars.view.adapter.AdapterCharacter;
import com.starwars.view.base.BaseActivity;
import com.starwars.view.ui.fragment.CharacterDetailFragment;
import com.starwars.viewmodel.CharacterViewModel;
import com.starwars.widget.AppLoadingDialog;

import java.util.List;

public class CharacterActivity extends BaseActivity implements AdapterCharacter.CharacterOnClick {

    private ActivityMainBinding binding;
    private AdapterCharacter adapterCharacter;
    private CharacterViewModel characterViewModel;
    private AppLoadingDialog progressDialog;
    private static String TAG = "CharacterActivity";

    private static final int PER_PAGE_SIZE = 10;
    private int previousTotal = 0;
    private boolean loading = false;
    private int pageNo = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initUI();
        binding.rvCharacter.addOnScrollListener(recyclerViewOnScrollListener);

    }

    private void initUI() {
        StarWarsApplication.getInstance().setForegroundActivity(this);
        serviceCall();
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int visibleThreshold = 5;
            int visibleItemCount = recyclerView.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }
            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                loading = true;
                fetchDataFromServer(pageNo);
            }
        }
    };

    private void serviceCall() {
        startProgress();
        characterViewModel = ViewModelProviders.of(this).get(CharacterViewModel.class);
        characterObserveViewModel(characterViewModel);
    }

    private void characterObserveViewModel(CharacterViewModel viewModel) {
        viewModel.getResult().observe(this, result -> {
            if (!result.isEmpty()) {
                pageNo = (result.size() / PER_PAGE_SIZE) + 1;
                if (adapterCharacter == null) {
                    adapterCharacter = new AdapterCharacter(this);
                    adapterCharacter.setCharacterList(result);
                    binding.rvCharacter.setAdapter(adapterCharacter);
                } else {
                    adapterCharacter.setCharacterList(result);
                }


            } else {
                fetchDataFromServer(pageNo);
            }
            stopProgress();
        });
    }

    private void fetchDataFromServer(int pageNo) {
        startProgress();
        characterViewModel.fetchFromServer(pageNo).observe(this, responseModels -> {
            stopProgress();
            if (responseModels != null) {
                validateResponse(responseModels);
            }
        });
    }

    private void validateResponse(List<ResultResponseModel> list) {
        characterViewModel.saveToDB(list);
    }

    @Override
    public void onClick(ResultResponseModel model, View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, CharacterDetailFragment.getInstance(model));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void startProgress() {
        if (progressDialog == null) {
            progressDialog = new AppLoadingDialog(this);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private void stopProgress() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
