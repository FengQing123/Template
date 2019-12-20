package com.example.template.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.template.R;
import com.example.template.bean.PersonBean;

import java.util.List;

/**
 * 功能描述：
 * Created by gfq on 2019/12/20.
 */
public class PersonAdapter extends BaseQuickAdapter<PersonBean, BaseViewHolder> {

    public PersonAdapter(@Nullable List<PersonBean> data) {
        super(R.layout.item_recycleview, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PersonBean item) {
        helper.setText(R.id.tv_name, item.getName());
    }
}
