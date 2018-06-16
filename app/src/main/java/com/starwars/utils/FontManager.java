package com.starwars.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class FontManager {
    private static FontManager sInstance;
    private final Map<String, Typeface> mTypefaceCache;

    private FontManager() {
        mTypefaceCache = new HashMap<>();
    }

    public static FontManager getInstance() {
        if (sInstance == null) {
            sInstance = new FontManager();
        }

        return sInstance;
    }

    public Typeface getTypeface(Context context, int resId) {
        String customFontName = context.getString(resId);
        return getTypeface(context, customFontName);
    }

    public Typeface getTypeface(Context context, String customFontName) {
        Typeface typeface = mTypefaceCache.get(customFontName);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), customFontName);
            mTypefaceCache.put(customFontName, typeface);
        }
        return typeface;
    }

    public void setFont(TextView textView, String customFontName) {
        if (customFontName == null || textView == null) {
            return;
        }

        Typeface typeface = mTypefaceCache.get(customFontName);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(textView.getContext().getAssets(), customFontName);
            mTypefaceCache.put(customFontName, typeface);
        }

        textView.setTypeface(typeface);
    }
}
