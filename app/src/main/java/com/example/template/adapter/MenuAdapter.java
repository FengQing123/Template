package com.example.template.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.template.R;
import com.example.template.bean.HomeMenuBean;

import java.util.List;

/**
 * 功能描述：主页RecycleView adapter
 * Created by gfq on 2020/3/26.
 */
public class MenuAdapter extends BaseQuickAdapter<HomeMenuBean, BaseViewHolder> {

    public MenuAdapter(@Nullable List<HomeMenuBean> data) {
        super(R.layout.item_menu, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeMenuBean item) {
        helper.setText(R.id.tv_menu_name, item.getName());
    }
}
