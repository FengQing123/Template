package com.example.template.jetpack.livedata;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.common.util.UIUtil;
import com.example.template.R;
import com.example.template.app.BaseActivity;

/**
 * 功能描述：
 * Created by gfq on 2020/3/27.
 */
public class LiveDataTestActivity extends BaseActivity {

    private NameViewModel model;

    @Override
    protected int getLayout() {
        return R.layout.activity_livedata;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView mTvName = findViewById(R.id.tv_name);

        //获取ViewModel
        model = ViewModelProviders.of(this).get(NameViewModel.class);

        //创建Observer对象，用于更新UI
        final Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTvName.setText(s);
            }
        };

        //观察LiveData,传递LifecycleOwner和Observer
        model.getCurrentName().observe(this, observer);

        findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * 使用setValue改变LiveData的值
                 *
                 * 注意：主线程用setValue,工作线程用postValue()
                 */
                model.getCurrentName().setValue("Android=" + (model.i++));
            }
        });


        findViewById(R.id.btn_liveDataBus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UIUtil.showActivity(mActivity, LiveDataBusTestActivity.class);

                //主线程设置LiveData数据使用setValue();
                LiveDataBus.getInstance().with("data", String.class).setValue("Android");

//                new Thread() {
//                    @Override
//                    public void run() {
//                        while (true) {
//                            //工作线程设置LiveData数据使用postValue()
//                            LiveDataBus.getInstance().with("data", String.class).postValue("Android");
//                            try {
//                                Thread.sleep(5000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }.start();
            }
        });


    }
}
