package com.starwars.services.types;

import android.support.annotation.NonNull;

import com.starwars.R;
import com.starwars.application.StarWarsApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class IsoDateWithoutMs extends Date {

    public IsoDateWithoutMs(long milliseconds) {
        super(milliseconds);
    }

    public static IsoDateWithoutMs convertFromString(String dateString) {
        final DateFormat format = getDateFormat();
        long milliseconds;
        try {
            milliseconds = format.parse(dateString).getTime();
        } catch (ParseException e) {
            milliseconds = 0;
        }

        return new IsoDateWithoutMs(milliseconds);
    }

    @NonNull
    public static DateFormat getDateFormat() {
        DateFormat format = new SimpleDateFormat(StarWarsApplication.getInstance().getString(R.string.iso_date_format_string_2), Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return format;
    }

    public String convertToString() {
        return getDateFormat().format(this);
    }

    @Override
    public String toString() {
        DateFormat format = getDateFormat();
        return format.format(this);
    }
}
