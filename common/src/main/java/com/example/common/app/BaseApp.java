package com.example.common.app;

import android.app.Application;
import android.content.Context;

/**
 * 功能描述：Common库中的APP
 * Created by gfq on 2019/12/20.
 */
public class BaseApp extends Application {

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
