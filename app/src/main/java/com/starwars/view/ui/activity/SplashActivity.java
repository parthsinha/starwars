package com.starwars.view.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private Handler handler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();
    }

    private void initUI() {
        setupUI();
    }

    private void setupUI() {
        handler = new Handler();
        mRunnable = () -> {
            Intent mIntent = new Intent(SplashActivity.this, CharacterActivity.class);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(mIntent);
            finish();
        };

        handler.postDelayed(mRunnable, 1000);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(mRunnable);
        super.onDestroy();
    }
}