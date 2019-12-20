package com.example.template.module.recycleview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.R;
import com.example.template.adapter.PersonAdapter;
import com.example.template.app.BaseActivity;
import com.example.template.bean.PersonBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 功能描述：
 * 参考文章：https://blog.csdn.net/lmj623565791/article/details/45059587
 * Created by gfq on 2019/12/20.
 */
public class RecycleViewTestActivity extends BaseActivity {

    @BindView(R.id.recycleview)
    RecyclerView mRecycleView;

    @Override
    protected int getLayout() {
        return R.layout.activity_recycleview;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<PersonBean> personBeans = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            PersonBean bean = new PersonBean("Android" + i);
            personBeans.add(bean);
        }

        PersonAdapter adapter = new PersonAdapter(personBeans);
        mRecycleView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return position == 0 ? 2 : 1;
//            }
//        });

        mRecycleView.setLayoutManager(layoutManager);

    }
}
