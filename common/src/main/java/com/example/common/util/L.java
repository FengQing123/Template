package com.example.common.util;

import android.util.Log;

/**
 * 功能描述：Log 打印工具类
 * Created by gfq on 2019/12/20.
 */
public class L {
    public static final String TAG = "LOG";
    private static final Boolean isOpen = true;

    /**
     * 普通版
     *
     * @param msg
     */
    public static void d(String msg) {
        if (isOpen) {
            //打开日志
            Log.d(TAG, msg);
        } else {
            //关闭日志
        }
    }

    /**
     * 进行String类型转换，所以我们可以随意传入任意类型的参数
     *
     * @param msg
     */
    public static void d(Object msg) {
        String string = msg.toString();
        Log.d(TAG, string);
    }
}
