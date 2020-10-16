package com.example.template.module.recycleview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.R;
import com.example.template.adapter.StarAdapter;
import com.example.template.app.BaseActivity;
import com.example.template.bean.Star;
import com.example.template.view.recycleview.StarDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 功能描述：
 * Created by gfq on 2020/10/16
 **/
public class RecycleViewTopActivity extends BaseActivity {

    @BindView(R.id.recycleview)
    RecyclerView mRecycleView;

    @Override
    protected int getLayout() {
        return R.layout.activity_recycleview_top;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Star> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 20; j++) {
                if (i % 2 == 0) {
                    list.add(new Star("语文成绩" + j, "语文" + i));
                } else {
                    list.add(new Star("数学成绩" + j, "数学" + i));
                }
            }
        }

        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.addItemDecoration(new StarDecoration(this));
        mRecycleView.setAdapter(new StarAdapter(list));
    }
}
