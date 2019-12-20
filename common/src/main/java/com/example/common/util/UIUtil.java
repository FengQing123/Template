package com.example.common.util;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 功能描述：
 * Created by gfq on 2019/12/20.
 */
public class UIUtil {

    public static void showActivity(AppCompatActivity activity, Class clz) {
        Intent intent = new Intent(activity, clz);
        activity.startActivity(intent);
    }
}
