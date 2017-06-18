package com.example.administrator.matchbox.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/11/29.
 */

public class SPHelper {

    private SharedPreferences sp;

    public SPHelper(Context context, String name) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }


    //存储多少种数据
    //int boolean float long String
    //存储方法
    public void save(ContentValue... contentValue) {
        SharedPreferences.Editor et = sp.edit();
        for (ContentValue value : contentValue) {
            if (value.value instanceof String) {
                et.putString(value.key, (String) value.value);
            } else if (value.value instanceof Integer)
                et.putInt(value.key, Integer.parseInt(value.value.toString()));
            else if (value.value instanceof Boolean) {
                et.putBoolean(value.key, Boolean.parseBoolean(value.value.toString()));
            }
        }
        et.commit();
    }

    public int getInt(String key) {
        return sp.getInt(key, -1);
    }

    public int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public String getString(String key) {
        return sp.getString(key, null);
    }

    public String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }


    public void clear() {
        sp.edit().clear().commit();
    }

    public static class ContentValue {
        String key;
        Object value;

        public ContentValue(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

}
