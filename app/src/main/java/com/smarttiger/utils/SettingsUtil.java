package com.smarttiger.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.DecimalFormat;

/**
 * Created by zhuxh on 2017/8/18.
 */

public class SettingsUtil {

    private static String RED_PACKAGE_AMOUNT = "red_package_amount";

    public static void setRedPackageAmount(Context context, long amount){
        SharedPreferences preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(RED_PACKAGE_AMOUNT, amount);
        editor.commit();
    }

    public static double getRedPackageAmount(Context context){
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        String s = preferences.getString(RED_PACKAGE_AMOUNT, "0");
        return Double.parseDouble(s);
    }

    public static void addRedPackageAmount(Context context, double redPackageNum){
        SharedPreferences preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        double amount = getRedPackageAmount(context);
        amount = amount + redPackageNum;
        DecimalFormat df = new DecimalFormat("#####0.00");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(RED_PACKAGE_AMOUNT, df.format(amount));
        editor.commit();
    }

}
