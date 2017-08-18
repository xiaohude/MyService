package com.smarttiger.utils;

import android.content.Context;

import com.smarttiger.myservice.ExpirationUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuxh on 2017/8/18.
 */

public class UMCollectUtil {

    public static final String CLICK_SERVICE_LAYOUT = "click_service_layout";
    public static final String CLICK_SERVICE_SWITCH = "click_service_switch";
    public static final String VERSION_TYPE_TRIAL = "version_type_trial";
    public static final String VERSION_TYPE_REGISTER = "version_type_register";
    public static final String VERSION_TYPE_EXPIRATION = "version_type_expiration";
    private static final String VERSION_SOURCE = "version_source";

    public static void collectEvent(Context context, String eventId){
        MobclickAgent.onEvent(context, eventId);
    }

    public static void collectVersionSource(Context context, String versionType){
        String id = ExpirationUtil.IMEI.substring(2,6) + ExpirationUtil.IMEI.substring(8, 14);
        Map<String, String> map_value = new HashMap<>();
        map_value.put("sourceID", id);
        map_value.put("versionType", versionType);
        MobclickAgent.onEvent(context, VERSION_SOURCE, map_value);
    }

}
