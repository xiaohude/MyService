package com.smarttiger.myservice;

import android.app.Application;

import com.smarttiger.utils.ObjectStore;

/**
 * Created by zhuxh on 2017/8/28.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ObjectStore.setContext(this);
    }
}
