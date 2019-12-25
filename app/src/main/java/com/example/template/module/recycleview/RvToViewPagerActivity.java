package com.example.template.module.recycleview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.R;
import com.example.template.adapter.NormalAdapter;
import com.example.template.app.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 功能描述：使用RecycleView 实现轮播图功能
 *
 * 有问题：
 * 1.不会居中，滑到哪停到哪（使用LinearSnapHelper解决）
 * 2.无法一次滑一个（）
 *
 *
 * Created by gfq on 2019/12/25.
 */
public class RvToViewPagerActivity extends BaseActivity {

    @BindView(R.id.recycleview)
    RecyclerView mRecycleView;

    @Override
    protected int getLayout() {
        return R.layout.activity_rv_to_vp;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mRecycleView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecycleView.setLayoutManager(layoutManager);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("Android" + i);
        }


        //可以解决item居中问题
        new LinearSnapHelper().attachToRecyclerView(mRecycleView);


        NormalAdapter adapter = new NormalAdapter(list);
        mRecycleView.setAdapter(adapter);


    }
}
