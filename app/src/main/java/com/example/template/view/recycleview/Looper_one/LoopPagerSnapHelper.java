package com.example.template.view.recycleview.Looper_one;

import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.view.recycleview.Looper_one.LooperLayoutManager;

/**
 * 功能描述：配合 {@link LooperLayoutManager} 解决一次滑动多页的问题（问题好像也没有解决）
 * Created by gfq on 2020/1/9
 **/
public class LoopPagerSnapHelper extends PagerSnapHelper {

    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        int position = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
        return position % layoutManager.getItemCount();
    }
}
