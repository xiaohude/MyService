package com.smarttiger.utils;

/**
 * Created by zhuxh on 2017/8/28.
 */

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;



/**
 * an global singleton object store, every object is associated with a unique String key.
 */
public final class ObjectStore {
    private static Map<String, Object> mObjects = new HashMap<String, Object>();

    private ObjectStore() {}

    /**
     * add object to store, return an unique key that can be used to query this object later.
     * @param obj storage object
     * @return key match object in object collections
     */
    public static String add(Object obj) {
        String key = UUID.randomUUID().toString();
        synchronized (mObjects) {
            mObjects.put(key, obj);
        }
        return key;
    }

    /**
     * add object to store, with specified unique key.
     * caller must assure the key is unique across all objects in this store. eg. add a custom unique prefix when generate key.
     * @param obj storage object
     * @return key the unique key used to get this object
     */
    public static void add(String key, Object obj) {
        synchronized (mObjects) {
            mObjects.put(key, obj);
        }
    }

    /**
     * query object by key.
     * @param key the key returned when add this object
     * @return the object associated with specified key, or null if not found
     */
    public static Object get(String key) {
        Object obj = null;
        synchronized (mObjects) {
            obj = mObjects.get(key);
        }
        return obj;
    }

    /**
     * remove object from store and return it.
     * @param key the key returned when add this object
     * @return the object associated with specified key, or null if not found
     */
    public static Object remove(String key) {
        Object obj = null;
        synchronized (mObjects) {
            obj = mObjects.remove(key);
        }
        return obj;
    }


    // the global application context.
    private static Context mContext = null;

    public static void setContext(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }
}
