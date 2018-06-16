package com.starwars.configuration;


import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

import com.starwars.BuildConfig;
import com.starwars.configuration.interfaces.Configuration;

import java.util.Locale;

public class StarWarsConfiguration implements Configuration {
    private static final StarWarsConfiguration ourInstance = new StarWarsConfiguration();
    private static android.content.res.Configuration configuration;

    public static StarWarsConfiguration getInstance() {
        return ourInstance;
    }

    @Override
    public boolean getLogVerbose() {
        return BuildConfig.DEBUG;
    }

    @Override
    public boolean getLogDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public boolean getLogWarn() {
        return BuildConfig.DEBUG;
    }

    @Override
    public boolean getLogError() {
        return true;
    }

    @Override
    public boolean getLogInfo() {
        return BuildConfig.DEBUG;
    }

    @Override
    public boolean getLogWtf() {
        return true;
    }

    @Override
    public void setCategorySelected(int categoryId) {

    }

    @Override
    public Locale getApplicationLocale() {
        if (configuration != null) {
            return configuration.locale;
        }
        return null;
    }

    @Override
    public void setApplicationLocale(String localeString, Activity activity) {
        Resources res = activity.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        configuration = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(new Locale(localeString));
        } else {
            Log.v("Locale Error:", "Error Setting up Locale");
        }
        res.updateConfiguration(configuration, dm);
    }
}
