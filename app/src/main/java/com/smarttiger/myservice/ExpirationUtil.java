package com.smarttiger.myservice;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhuxh on 2017/8/14.
 */

public class ExpirationUtil {

    public static String expirationTime = "2017/08/20 12:00" ;
    private static String IMEI = "867695020006101";

    public static boolean isExceedTime (Context context) {

        if(isRightIMEI(context))
            return false;

        long currentTime = System.currentTimeMillis();

        if(currentTime > time2long(expirationTime)) {
            //调起卸载app的界面
            Uri packageUri = Uri.parse("package:"+context.getPackageName());
            Intent intent = new Intent(Intent.ACTION_DELETE,packageUri);
            context.startActivity(intent);
            return true;
        }
        else
            return false;

    }

    public static long time2long(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.CHINA);
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


    private static boolean isRightIMEI (Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return  IMEI.equals(imei);
    }

    public static String getIMEI (Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

}
