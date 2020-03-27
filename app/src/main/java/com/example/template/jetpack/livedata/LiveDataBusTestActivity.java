package com.example.template.jetpack.livedata;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.template.R;
import com.example.template.app.BaseActivity;
import com.example.template.mvp.util.ToastUtil;

/**
 * 功能描述：
 * Created by gfq on 2020/3/27.
 */
public class LiveDataBusTestActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_livedata_bus;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LiveDataBus.getInstance().with("data", String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    ToastUtil.showToast(s);
                }
            }
        });
    }
}
