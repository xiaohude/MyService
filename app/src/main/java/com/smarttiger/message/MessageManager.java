package com.smarttiger.message;

import com.smarttiger.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuxh on 2017/8/14.
 */
public class MessageManager {

    private static MessageManager mInstance = new MessageManager();

    private List<Message> mMessageList = new ArrayList<>();
    private List<String> mMessages = new ArrayList<>();

    public static MessageManager getInstance() {
        if (mInstance == null) {
            mInstance = new MessageManager();
        }
        return mInstance;
    }

    private MessageManager() {
    }

    public void addMessage (Message message) {
        mMessageList.add(message);
    }

    public List<Message> getMessageList () {
        return mMessageList;
    }


    public void addMessages (String text) {
        mMessages.add(0, "["+ TimeUtil.getCurrentDateTimes() +"] " + text);
        if(mMessages.size() > 100)
            mMessages.remove(100);
    }

    public String getMessages () {
        String messages = "";
        for(String message : mMessages) {
            messages = messages + message +  "\n";
        }
        return messages;
    }
    public void cleanMessages () {
        mMessages.clear();
    }

    private boolean hasCatchNotificationChanged = false;
    public void setCatchNotificationChanged(boolean hasCatch) {
        hasCatchNotificationChanged = hasCatch;
    }
    public boolean hasCatchNotificationChanged() {
        return hasCatchNotificationChanged;
    }

    private boolean isCatchedMessage;
    public void setIsCatchedMessage(boolean isCatched) {
        isCatchedMessage = isCatched;
    }
    public boolean isCatchedMessage() {
        return isCatchedMessage;
    }
}
