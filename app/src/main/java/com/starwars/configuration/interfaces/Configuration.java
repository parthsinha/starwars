package com.starwars.configuration.interfaces;

import android.app.Activity;

import java.util.Locale;

public interface Configuration {

    boolean getLogVerbose();

    boolean getLogDebug();

    boolean getLogWarn();

    boolean getLogError();

    boolean getLogInfo();

    boolean getLogWtf();

    Locale getApplicationLocale();

    void setCategorySelected(int categoryId);

    void setApplicationLocale(String localeString, Activity activity);
}
