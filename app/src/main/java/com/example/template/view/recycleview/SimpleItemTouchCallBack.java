package com.example.template.view.recycleview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.adapter.PersonAnimatorAdapter;
import com.example.template.bean.PersonBean;

import java.util.Collections;
import java.util.List;

/**
 * 功能描述：通过Android 提供的 ItemTouchHelper 类，实现滑动和拖曳
 *
 * <pre>
 *     ItemTouchHelper helper = new ItemTouchHelper(new SimpleItemTouchCallBack(adapter,data));
 *     helper.attachToRecycleView(recycleView)
 * </pre>
 * Created by gfq on 2019/12/24.
 */
public class SimpleItemTouchCallBack extends ItemTouchHelper.Callback {

    private PersonAnimatorAdapter mAdapter;
    private List<PersonBean> mData;

    public SimpleItemTouchCallBack(PersonAnimatorAdapter adapter, List<PersonBean> mData) {
        this.mAdapter = adapter;
        this.mData = mData;
    }

    /**
     * 设置拖曳和滑动所支持的方法
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        //支持上下拖曳
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

        //支持 左到右 和 右到左 滑动
        int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlag, swipeFlag);
    }

    /**
     * 拖曳时回调
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        int from = viewHolder.getAdapterPosition();
        int to = target.getAdapterPosition();
        Collections.swap(mData, from, to);
        mAdapter.notifyItemMoved(from, to);
        return true;
    }

    /**
     * 滑动时回调
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                         int direction) {
        int pos = viewHolder.getAdapterPosition();
        mData.remove(pos);
        mAdapter.notifyItemRemoved(pos);
    }

    /**
     * 状态变化时回调，一共有三个状态，分别是
     * ACTION_STATE_IDLE(空闲状态)，ACTION_STATE_SWIPE(滑动状态)，ACTION_STATE_DRAG(拖拽状态)。
     * 此方法中可以做一些状态变化时的处理，比如拖拽的时候修改背景色
     *
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder,
                                  int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            PersonAnimatorAdapter.VH holder = (PersonAnimatorAdapter.VH) viewHolder;
            //设置拖曳和侧滑时的背景色
            holder.itemView.setBackgroundColor(0xffbcbcbc);
        }
    }

    /**
     * 用户交互结束时回调。此方法可以做一些状态的清空，比如拖拽结束后还原背景色
     *
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        PersonAnimatorAdapter.VH holder = (PersonAnimatorAdapter.VH) viewHolder;
        //设置拖曳和侧滑时的背景色
        holder.itemView.setBackgroundColor(0xffeeeeee);
    }

    /**
     * 是否支持长按拖拽，默认为true。如果不想支持长按拖拽，则重写并返回false。
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }
}
