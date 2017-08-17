package com.smarttiger.myservice;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import com.smarttiger.message.MessageManager;

/**
 * Created by zhuxh on 2017/8/15.
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class MyNotificationListenerService extends NotificationListenerService {

    private final String WEIXIN_PACKAGE= "com.tencent.mm";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        System.out.println("onNotificationPosted-----------packageName="+sbn.getPackageName());
        System.out.println("onNotificationPosted-----------tickerText="+sbn.getNotification().tickerText);
//        System.out.println("onNotificationPosted-----------extras="+sbn.getNotification().extras);

        String packageName = sbn.getPackageName();
        if(WEIXIN_PACKAGE.equals(packageName)) {
            if(MessageManager.getInstance().hasCatchNotificationChanged())
                return;
            String text = sbn.getNotification().tickerText.toString();
            MessageManager.getInstance().addMessages(text);
            if(text.contains("[微信红包]"))
            {
                try {
                    sbn.getNotification().contentIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }

            MessageManager.getInstance().setIsCatchedMessage(true);
        }

    }


    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

    }

    private void cancelNotification(StatusBarNotification sbn) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            cancelNotification(sbn.getKey());
        else
            cancelNotification(sbn.getPackageName(), sbn.getTag(), sbn.getId());
    }

}
