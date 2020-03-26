package com.example.template;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.common.util.UIUtil;
import com.example.template.adapter.MenuAdapter;
import com.example.template.app.BaseActivity;
import com.example.template.bean.HomeMenuBean;
import com.example.template.module.CalendarActivity;
import com.example.template.module.DefineFlowLayoutActivity;
import com.example.template.module.DefineTextViewActivity;
import com.example.template.module.RxJavaActivity;
import com.example.template.module.ViewPagerActivity;
import com.example.template.module.notification.NotificationActivity;
import com.example.template.module.recycleview.RecycleViewTestActivity;
import com.example.template.module.recycleview.RvToViewPagerActivity;
import com.example.template.mvp.model.login.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {

    private List<HomeMenuBean> mList = new ArrayList<>();

    {
        mList.add(new HomeMenuBean("ViewPager的使用", ViewPagerActivity.class));
        mList.add(new HomeMenuBean("RecycleView", RecycleViewTestActivity.class));
        mList.add(new HomeMenuBean("RecycleView实现轮播图", RvToViewPagerActivity.class));
        mList.add(new HomeMenuBean("监听通知消息", NotificationActivity.class));
        mList.add(new HomeMenuBean("自定义TextView", DefineTextViewActivity.class));
        mList.add(new HomeMenuBean("日期学习", CalendarActivity.class));
        mList.add(new HomeMenuBean("RxJava学习", RxJavaActivity.class));
        mList.add(new HomeMenuBean("MVP架构", LoginActivity.class));
        mList.add(new HomeMenuBean("自定义FlowLayout", DefineFlowLayoutActivity.class));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView mRecycleView = findViewById(R.id.recycleview);
        mRecycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        MenuAdapter mAdapter = new MenuAdapter(mList);
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            HomeMenuBean bean = mList.get(position);
            UIUtil.showActivity(mActivity, bean.getClz());
        });
    }

}
