package com.example.template.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.template.R;
import com.example.template.bean.DateCalendarBean;

import java.util.List;

/**
 * 功能描述：
 * Created by gfq on 2020/1/9.
 */
public class CalendarAdapter extends BaseQuickAdapter<DateCalendarBean, BaseViewHolder> {

    public CalendarAdapter(@Nullable List<DateCalendarBean> data) {
        super(R.layout.item_calendar_layout, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DateCalendarBean item) {

    }

    /**
     * 重写此方法，使RecycleView可以一直滑动
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    /**
     * 重写getItem以免出现无限滑动时当position大于data的size时获得对象为空
     *
     * @param position
     * @return
     */
    @Nullable
    @Override
    public DateCalendarBean getItem(int position) {
        int newPosition = position % getData().size();
        return getData().get(newPosition);
    }

    /**
     * 重写此方法，因为BaseQuickAdapter里绘制view时会调用此方法判断，
     * position减去getHeaderLayoutCount小于data.size()时才会调用调用cover方法绘制我们自定义的view
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int count = getHeaderLayoutCount() + getData().size();
        //刚开始进入包含该类的activity时,count为0。就会出现0%0的情况，这会抛出异常，所以我们要在下面做一下判断
        if (count <= 0) {
            count = 1;
        }
        int newPosition = position % count;
        return super.getItemViewType(newPosition);
    }
}
