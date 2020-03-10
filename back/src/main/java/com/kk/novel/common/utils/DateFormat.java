package com.kk.novel.common.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    public static String dateToString (Date date) {
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.format(date);
        }
        return null;
    }

    public static Date stringToDate (String s) throws ParseException {
        if (!"".equals(s)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.parse(s);
        }
        return null;
    }
}
