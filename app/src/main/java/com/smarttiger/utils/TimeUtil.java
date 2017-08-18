package com.smarttiger.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhuxh on 2017/8/15.
 */

public class TimeUtil {


    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static String getCurrentDateTimes() {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd HH:mm:ss", Locale.getDefault());
        return format.format(new Date());
    }

    public static long time2long(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA);
        Date date;
        long longTime = 0;
        try {
            date = sdr.parse(time);
            longTime = date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return longTime;
    }

    public static String long2time(long l) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        return format.format(l);
    }

}
