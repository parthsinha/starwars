package com.starwars.services.types;

import android.content.Context;

import com.starwars.R;
import com.starwars.application.StarWarsApplication;
import com.starwars.logging.StarWarsLog;
import com.starwars.utils.TagUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class IsoDate extends Date {
    public IsoDate() {
        super();
    }

    public IsoDate(long milliseconds) {
        super(milliseconds);
    }

    public static IsoDate convertFromString(String dateString) {
        List<String> dateFormats = getDateFormats();
        long milliseconds = 0;
        boolean success = false;
        for (String dateFormatString : dateFormats) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatString, Locale.getDefault());
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                milliseconds = simpleDateFormat.parse(dateString).getTime();
                success = true;
                break;
            } catch (ParseException e1) {
                if (dateFormats.indexOf(dateFormatString) == dateFormats.size() - 1) {
                    e1.printStackTrace();
                }
            }
        }
        if (!success) {
            milliseconds = 0;
            StarWarsLog.e(TagUtil.shortFrom(IsoDate.class), "Could not parse " + dateString + " as an IsoDate, using epoch instead (1970-01-01)");
        }
        return new IsoDate(milliseconds);
    }

    public static DateFormat getDateFormat() {
        DateFormat format = new SimpleDateFormat(StarWarsApplication.getInstance().getString(R.string.iso_date_format_string_1), Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return format;
    }

    private static List<String> getDateFormats() {
        ArrayList<String> dateFormats = new ArrayList<>();
        Context context = StarWarsApplication.getInstance();
        dateFormats.add(context.getString(R.string.iso_date_format_string_1));
        dateFormats.add(context.getString(R.string.iso_date_format_string_2));
        dateFormats.add(context.getString(R.string.iso_date_format_string_3));
        dateFormats.add(context.getString(R.string.iso_date_format_string_4));
        dateFormats.add(context.getString(R.string.iso_date_format_string_5));
        dateFormats.add(context.getString(R.string.iso_date_format_string_6));
        dateFormats.add(context.getString(R.string.iso_date_format_string_7));
        return dateFormats;
    }

    public String convertToString() {
        return getDateFormat().format(this);
    }

    public String convertToString(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(this);
    }

    @Override
    public String toString() {
        DateFormat format = getDateFormat();
        return format.format(this);
    }
}
