package com.example.common.app;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

/**
 * 功能描述：Common库中的APP
 * Created by gfq on 2019/12/20.
 */
public class BaseApp extends MultiDexApplication {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
