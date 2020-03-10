package com.example.mvpjava.base;

import android.app.Application;

import com.example.mvpjava.util.ActivityUtil;
import com.example.mvpjava.util.YUtils;

/**
 * 功能描述：
 * Created by gfq on 2020/3/10.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        YUtils.initialize(this);
        //注册Activity生命周期
        registerActivityLifecycleCallbacks(ActivityUtil.getActivityLifecycleCallbacks());
    }
}
