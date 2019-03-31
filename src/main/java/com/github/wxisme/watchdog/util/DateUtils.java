package com.github.wxisme.watchdog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class DateUtils {

    final static String DATE_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    final static String TIME_ZONE = "GMT";

    static String getStandardDateString() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_STRING);
        df.setTimeZone(new SimpleTimeZone(0, TIME_ZONE));
        return df.format(new Date());
    }

    static Long getStandardDateMillis() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE));
        return calendar.getTimeInMillis();
    }

    static Date getStandardDate(String dateTimeString) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_STRING);
        df.setTimeZone(new SimpleTimeZone(0, TIME_ZONE));
        Date date = null;
        try {
            date = df.parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
