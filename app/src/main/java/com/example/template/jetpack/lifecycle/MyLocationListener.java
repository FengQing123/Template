package com.example.template.jetpack.lifecycle;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 功能描述：该类需要实现LifecycleObserver,感知实现LifecycleOwner类的生命周期
 * <p>
 * 生命周期感知型组件可执行操作来响应另一个组件（如 Activity 和 Fragment）的生命周期状态的变化。
 * 这些组件有助于您写出更有条理且往往更精简的代码，这样的代码更易于维护。
 * <p>
 * Created by gfq on 2020/3/27.
 */
public class MyLocationListener implements LifecycleObserver {

    private static final String TAG = "MyLocationListener";

    private CallBack mCallBack;

    private Context mContext;

    private Lifecycle mLifeCycle;

    private boolean enabled = false;

    public MyLocationListener(Context mContext, Lifecycle lifecycle, CallBack callBack) {
        this.mLifeCycle = lifecycle;
        this.mCallBack = callBack;
    }

    public void enable() {
        enabled = true;
        if (mLifeCycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            //connect if not connected
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create() {
        Log.e(TAG, "hhhh-create");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        if (enabled) {
            Log.e(TAG, "hhhh-start");
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume() {
        Log.e(TAG, "hhhh-resume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause() {
        Log.e(TAG, "hhhh-pause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        Log.e(TAG, "hhhh-stop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy() {
        Log.e(TAG, "hhhh-destroy");
    }

    public interface CallBack {
        void updateUI();
    }


}
