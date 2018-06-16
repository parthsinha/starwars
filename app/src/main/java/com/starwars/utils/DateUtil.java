package com.starwars.utils;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static DateUtil dateUtil;

    private DateUtil() {
    }

    public static DateUtil getInstance() {
        if (dateUtil == null) {
            dateUtil = new DateUtil();
        }
        return dateUtil;
    }

    public String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public String getTimeStamp(String date) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss.SSS");
        Date now = new Date();
        String strTime = sdfDate.format(now);
        String strDate = date + " " + strTime;
        return strDate;
    }

    public String getCurrentTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat(" HH:mm:ss");
        Date now = new Date();
        return sdfDate.format(now);
    }

    public CharSequence getFormattedDate(int day, int month, int year) {
        return new StringBuilder().append(String.format(Locale.getDefault(), "%02d", month + 1)).append("/").append(String.format(Locale.getDefault(), "%02d", day)).append("/").append(year);
    }

    @BindingAdapter({"bind:dateConvertor"})
    public static void getDateDesiredFormat(TextView textView, String date) {
        String reformattedDate = "";

        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {
            reformattedDate = myFormat.format(fromUser.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        textView.setText("Created at : " + reformattedDate);
    }

    public String getDateDesiredFormat2(String date) {
        String reformattedDate = "";

        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MMM-yyyy");

        try {
            reformattedDate = myFormat.format(fromUser.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return reformattedDate;
    }
}
