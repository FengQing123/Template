package com.example.template.jetpack.databinding;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.template.BR;
import com.example.template.R;
import com.example.template.databinding.ActivityDatabindingBinding;

/**
 * 功能描述：
 * Created by gfq on 2020/3/31.
 */
public class DataBindingTestActivity extends AppCompatActivity {

    ActivityDatabindingBinding binding;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        binding.tv1.setText("Android");
        user = new User("Android", 19);
        binding.setUser(user);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        user.setName(user.getName() + "1");
                        binding.setUser(user);
                        binding.setVariable(BR.user, user);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
