package com.example.template.jetpack.lifecycle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.common.util.UIUtil;
import com.example.template.R;
import com.example.template.app.BaseActivity;
import com.example.template.module.recycleview.RecycleViewTestActivity;

/**
 * 功能描述：
 * Created by gfq on 2020/3/27.
 */
public class LifeCycleTestActivity extends BaseActivity {

    private static final String TAG = "LifeCycleTestActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_lifecycle;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtil.showActivity(mActivity, RecycleViewTestActivity.class);
            }
        });


        MyLocationListener locationListener = new MyLocationListener(this, getLifecycle(), () -> {

        });

        /**
         * 获取LifecycleObserver的实现类MyLocationListener
         * 并使用 addObserver方法 添加
         */
        getLifecycle().addObserver(locationListener);
        locationListener.enable();

        Log.e(TAG, "hhhh-onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "hhhh-onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "hhhh-onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "hhhh-onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "hhhh-onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "hhhh-onDestroy");

    }
}
