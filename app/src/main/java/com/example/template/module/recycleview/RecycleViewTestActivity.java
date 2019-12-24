package com.example.template.module.recycleview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.R;
import com.example.template.adapter.NormalAdapter;
import com.example.template.adapter.PersonAdapter;
import com.example.template.adapter.PersonAnimatorAdapter;
import com.example.template.app.BaseActivity;
import com.example.template.bean.PersonBean;
import com.example.template.view.recycleview.DividerGridItemDecoration;
import com.example.template.view.recycleview.DividerItemDecoration;
import com.example.template.view.recycleview.EmptyRecycleView;
import com.example.template.view.recycleview.SimpleItemTouchCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.gmariotti.recyclerview.itemanimator.ScaleInOutItemAnimator;

/**
 * 功能描述：
 * 参考文章：
 * https://blog.csdn.net/lmj623565791/article/details/45059587
 * https://blog.csdn.net/whdAlive/article/details/80539976
 * <p>
 * Created by gfq on 2019/12/20.
 */
public class RecycleViewTestActivity extends BaseActivity {

    @BindView(R.id.recycleview)
    EmptyRecycleView mRecycleView;


    private PersonAnimatorAdapter adapter;
    private List<PersonBean> personBeans = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_recycleview;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        for (int i = 0; i < 20; i++) {
            PersonBean bean = new PersonBean("Android" + i);
            personBeans.add(bean);
        }

        adapter = new PersonAnimatorAdapter(personBeans);


//        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return position == 0 ? 2 : 1;
//            }
//        });

        mRecycleView.setLayoutManager(layoutManager);

        /**
         * 添加分割线
         */
        mRecycleView.addItemDecoration(new DividerItemDecoration(mRecycleView.getContext(), layoutManager.getOrientation()));
//        mRecycleView.addItemDecoration(new DividerGridItemDecoration(mRecycleView.getContext()));

        /**
         * 设置动画
         */
        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        /**
         * 实现拖曳和删除
         */
        ItemTouchHelper helper = new ItemTouchHelper(new SimpleItemTouchCallBack(adapter, personBeans));
        helper.attachToRecyclerView(mRecycleView);

        /**
         * 实现对某个view触摸拖曳
         */
        adapter.setDragListener(new PersonAnimatorAdapter.OnStartDragListener() {
            @Override
            public void startDrag(RecyclerView.ViewHolder holder) {
                helper.startDrag(holder);
            }
        });

        /**
         * 实现空数据展示空页面
         */
        View mEmpty = LayoutInflater.from(mActivity).inflate(R.layout.empty_view, null);
        mRecycleView.setEmptyView(mEmpty);


        /**
         * 设置Adapter
         */
        mRecycleView.setAdapter(adapter);
    }

    @OnClick({R.id.btn_add, R.id.btn_delete, R.id.btn_move, R.id.btn_change})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                PersonBean personBean = new PersonBean("Android" + Math.random());
                adapter.addData(3, personBean);
                break;
            case R.id.btn_delete:
                adapter.removeData(3);
                break;
            case R.id.btn_move:
                adapter.moveData(0, 3);
                break;
            case R.id.btn_change:
                PersonBean personBean3 = new PersonBean("Android" + Math.random());
                adapter.notifyData(3, personBean3);
                break;
        }
    }
}
