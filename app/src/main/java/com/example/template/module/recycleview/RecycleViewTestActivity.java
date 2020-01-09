package com.example.template.module.recycleview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.R;
import com.example.template.adapter.PersonAnimatorAdapter;
import com.example.template.app.BaseActivity;
import com.example.template.bean.PersonBean;
import com.example.template.dialog.LevelBottomDialog;
import com.example.template.dialog.LevelDialog;
import com.example.template.view.recycleview.DividerItemDecoration;
import com.example.template.view.recycleview.EmptyRecycleView;
import com.example.template.view.recycleview.SimpleItemTouchCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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

        /**
         * 用于解决滑动冲突问题
         * 例如：RecycleView嵌套在DrawableLayout里，左滑RecycleView无法滑动，DrawableLayout却被左滑了
         *
         * 原因是：RecycleView在处理水平滚动时，被DrawableLayout拦截了（先消费掉了)，
         * 解决方法：我们可以在RecycleView收到Action_MOVE时，请求父布局不要拦截它的事件
         * 具体解决方法：
         * 1.给RecycleView设置一个OnTouchListener()方法
         * 2.Action_MOVE里，调用它的getParent方法返回的ViewParent对象的requestDisallowInterceptTouchEvent方法，参数传true,表示不允许拦截事件
         * 3.在ACTION_UP 和 ACTION_CANCEL里，调用requestDisallowInterceptTouchEvent方法，参数传false,无需处理滑动事件
         * 4.最后,onTouch方法一定要返回false(用来告诉RecycleView,它要继续处理触摸事件)
         *
         * 但是RecycleView开始滚动，那么DrawableLayout就无法关闭了
         * 用户想要的是：当列表滚动到最右边，手指继续向左滑动的时候，DrawableLayout就要响应事件
         * 我们可以使用View的canScrollHorizontally方法来判断，它的参数是int值，正数检测是否能向右滚动，负数检测是否能向左滚动
         *
         */
        mRecycleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_MOVE:
                        v.getParent().requestDisallowInterceptTouchEvent(v.canScrollHorizontally(1));
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
    }

    @OnClick({R.id.btn_add, R.id.btn_delete, R.id.btn_move, R.id.btn_change, R.id.btn_dialog, R.id.btn_BottomDialog})
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
            case R.id.btn_dialog:
                LevelDialog.newInstance().show(getSupportFragmentManager(), "levelDialog");
                break;
            case R.id.btn_BottomDialog:
                LevelBottomDialog.newInstance().show(getSupportFragmentManager(), "LevelBottomDialog");
                break;
        }
    }
}
