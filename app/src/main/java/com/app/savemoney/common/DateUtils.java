package com.app.savemoney.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DateUtils {

    public static String dateToString(Date date) {
        String dateStr = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat(CommonCodeValues.DATE_YYYYMMDD);

            dateStr = format.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateStr;

    }

    public static String dateToString(Date date, String formatStr) {
        String dateStr = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);

            dateStr = format.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateStr;
    }

    public static Date StringToDate(String dateStr, String formatStr) {
        Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            date = format.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

}
