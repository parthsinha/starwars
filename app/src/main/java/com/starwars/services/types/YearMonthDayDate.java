package com.starwars.services.types;

import com.starwars.logging.StarWarsLog;
import com.starwars.utils.TagUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class YearMonthDayDate extends Date {
    public YearMonthDayDate(String yearMonthDay) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        Date date;

        try {
            date = format.parse(yearMonthDay);
        } catch (ParseException e) {
            StarWarsLog.w(TagUtil.shortFrom(this), "Could not parse date: " + yearMonthDay);
            date = new Date(0);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        this.setTime(calendar.getTimeInMillis());
    }
}
