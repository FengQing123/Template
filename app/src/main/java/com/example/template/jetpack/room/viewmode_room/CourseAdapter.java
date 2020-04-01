package com.example.template.jetpack.room.viewmode_room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.template.R;

import java.util.List;

/**
 * 功能描述：
 * Created by gfq on 2020/4/1.
 */
public class CourseAdapter extends BaseQuickAdapter<Course, BaseViewHolder> {

    public CourseAdapter(@Nullable List<Course> data) {
        super(R.layout.item_course, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Course item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_score, item.getScore());
    }
}
