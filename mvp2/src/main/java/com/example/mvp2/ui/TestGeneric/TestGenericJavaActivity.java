package com.example.mvp2.ui.TestGeneric;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collection;

/**
 * 功能描述：
 * Created by gfq on 2020/1/15.
 */
public class TestGenericJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    void copyAll(Collection<Object> to, Collection<String> from) {
        to.addAll(from);
    }
}
