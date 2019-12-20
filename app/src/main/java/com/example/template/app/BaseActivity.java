package com.example.template.app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * 功能描述：
 * Created by gfq on 2019/12/20.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected BaseActivity mActivity;

    protected abstract int getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        mActivity = this;
    }

}
