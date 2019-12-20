package com.example.common.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.common.app.BaseApp;

/**
 * 功能描述：SharedPreferences存储工具类
 * * Created by gfq on 2019/12/20.
 */
public class ACache {

    private static Context mContext = BaseApp.getContext();

    public static void save() {
        SharedPreferences.Editor editor = mContext.getSharedPreferences("data", Context.MODE_PRIVATE).edit();
        editor.putString("name", "tonjies");
        editor.putInt("age", 20);
        editor.apply();
    }

    public static void get() {
        SharedPreferences preferences = mContext.getSharedPreferences("data", Context.MODE_PRIVATE);
        String name = preferences.getString("name", "");
        int age = preferences.getInt("age", 18);
        L.d("name:" + name);
        L.d("age" + age);
    }
}
