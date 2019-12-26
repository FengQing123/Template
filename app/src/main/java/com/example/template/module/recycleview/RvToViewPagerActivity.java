package com.example.template.module.recycleview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.R;
import com.example.template.adapter.NormalAdapter;
import com.example.template.app.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 功能描述：使用RecycleView 实现轮播图功能
 * <p>
 * 有问题：
 * 1.不会居中，滑到哪停到哪
 * 2.无法一次滑一个
 * <p>
 * LinearSnapHelper可以解决item居中问题
 * PagerSnapHelper可以使RecycleView像ViewPager一样的效果，每次只能滑动一次
 * <p>
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


        //LinearSnapHelper可以解决item居中问题
        new LinearSnapHelper().attachToRecyclerView(mRecycleView);

        //PagerSnapHelper可以使RecycleView像ViewPager一样的效果，每次只能滑动一次
//        new PagerSnapHelper().attachToRecyclerView(mRecycleView);


        NormalAdapter adapter = new NormalAdapter(list);
        mRecycleView.setAdapter(adapter);

        setScale(mRecycleView);


    }


    /**
     * 这个方法主要是对RecycleView的item进行缩放，实现画廊功能
     *
     * @param recyclerView
     */
    private void setScale(RecyclerView recyclerView) {
        //recycleView滑动监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //总item的数量
                int childCount = recyclerView.getChildCount();
                //第一个item的宽度
                int width = recyclerView.getChildAt(0).getWidth();
                //这个padding是 recycler的宽度减去第一个item的宽度然后除以2，作为padding
                int padding = (recyclerView.getWidth() - width) / 2;
                for (int j = 0; j < childCount; j++) {
                    //获取每一个child
                    View v = recyclerView.getChildAt(j);
                    //是一个缩放比例
                    float rate = 0;
                    //如果view距离左边的宽度 小于等于 左侧剩余空间(padding) （意味着这个view开始往左边滑动了，并且有遮挡）
                    if (v.getLeft() <= padding) {
                        //如果view距离左边的距离 小于等于滑进去的距离 （其实就是说滑动到一半的时候）
                        if (v.getLeft() >= padding - v.getWidth()) {
                            //（这个比例的计算结果一般都会大于1，这样一来，根据下面的 1- rate * 0.1 得出，这个比例最多不会到达1，
                            // 也就是 1- 0.1， 也就是 0.9，
                            // 所以这个view的宽度最大不会小于他本身的90%）
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        v.setScaleY(1 - rate * 0.1f);
                    } else {
                        //这个过程大概是指这个view 从最后侧刚刚出现的时候开始滑动过padding的距离
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                        }
                        v.setScaleY(0.9f + rate * 0.1f);
                    }
                }
            }
        });


        //加载完成后的监听
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (recyclerView.getChildCount() < 3) {
                    if (recyclerView.getChildAt(1) != null) {
                        View v1 = recyclerView.getChildAt(1);
                        v1.setScaleY(0.9f);
                    }
                } else {
                    if (recyclerView.getChildAt(0) != null) {
                        View v0 = recyclerView.getChildAt(0);
                        v0.setScaleY(0.9f);
                    }
                    if (recyclerView.getChildAt(2) != null) {
                        View v2 = recyclerView.getChildAt(2);
                        v2.setScaleY(0.9f);
                    }
                }
            }
        });

    }

}
