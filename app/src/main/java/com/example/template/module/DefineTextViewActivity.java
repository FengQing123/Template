package com.example.template.module;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.common.util.L;
import com.example.template.R;
import com.example.template.app.BaseActivity;

/**
 * 功能描述：
 * Created by gfq on 2020/1/2.
 */
public class DefineTextViewActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_define_view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.tv_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.e("------up------");
            }
        });
    }
}
