package com.smarttiger.myservice;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;

import com.smarttiger.utils.TimeUtil;

/**
 * Created by zhuxh on 2017/8/14.
 */

public class ExpirationUtil {

    public static String expirationTime = "2017/08/31 12:00";
    private static String IMEI = "867695020006101";

    public static boolean isExceedTime (Context context) {

        if(isRightIMEI(context))
            return false;

        long currentTime = TimeUtil.getCurrentTime();

        if(currentTime > TimeUtil.time2long(expirationTime)) {
            //调起卸载app的界面
            Uri packageUri = Uri.parse("package:"+context.getPackageName());
            Intent intent = new Intent(Intent.ACTION_DELETE,packageUri);
            context.startActivity(intent);
            return true;
        }
        else
            return false;

    }

    public static boolean isRightIMEI (Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return  IMEI.equals(imei);
    }

    public static String getIMEI (Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

}
