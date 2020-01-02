package com.example.common.util;

import android.util.Log;

/**
 * 功能描述：Log 打印工具类
 * Created by gfq on 2019/12/20.
 */
public class L {
    public static final String TAG = "LOG";
    private static final Boolean isOpen = true;

    public static void d(String msg) {
        L.d(TAG, msg);
    }

    public static void d(String TAG, String msg) {
        if (isOpen) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        L.e(TAG, msg);
    }

    public static void e(String TAG, String msg) {
        if (isOpen) {
            Log.e(TAG, msg);
        }
    }

    /**
     * 进行String类型转换，所以我们可以随意传入任意类型的参数
     *
     * @param msg
     */
    public static void d(Object msg) {
        String string = msg.toString();
        L.d(TAG, string);
    }

    public static void e(Object msg) {
        String string = msg.toString();
        L.e(TAG, string);
    }
}
