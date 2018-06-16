package com.starwars.utils;

import android.databinding.BindingAdapter;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.TextView;

public class Bindings {
    @BindingAdapter({"font"})
    public static void setFont(TextView textView, @StringRes int resId) {
        String fontName = textView.getResources().getString(resId);
        if (!TextUtils.isEmpty(fontName)) {
            setFont(textView, fontName);
        }
    }

    @BindingAdapter({"font"})
    public static void setFont(TextView textView, String fontName) {
        FontManager.getInstance().setFont(textView, fontName);
    }
}
