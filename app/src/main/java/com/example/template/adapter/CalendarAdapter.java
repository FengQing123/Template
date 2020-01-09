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

}
