package com.starwars.application;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.facebook.stetho.Stetho;
import com.starwars.internet.InternetCheck;
import com.starwars.internet.InternetCheckListener;

import java.lang.ref.WeakReference;

public class StarWarsApplication extends Application {
    private static WeakReference<StarWarsApplication> application;
    private WeakReference<AppCompatActivity> foregroundActivity;

    public static StarWarsApplication getInstance() {
        return application.get();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = new WeakReference<>(this);
        Stetho.initializeWithDefaults(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public AppCompatActivity getForegroundActivity() {
        return foregroundActivity != null ? foregroundActivity.get() : null;
    }

    public void setForegroundActivity(AppCompatActivity foregroundActivity) {
        this.foregroundActivity = new WeakReference<>(foregroundActivity);
    }

    public void setInternetCheckListner(InternetCheckListener internetCheckListener) {
        InternetCheck.checkInternetListener = internetCheckListener;
    }
}
