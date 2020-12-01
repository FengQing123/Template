package com.example.appkotlin.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.appkotlin.R
import com.example.appkotlin.bean.HomeMenuBean

/**
 *
 * 功能描述：
 * Created by gfq on 2020/12/1
 *
 **/

class MenuAdapter(data: List<HomeMenuBean>?) : BaseQuickAdapter<HomeMenuBean, BaseViewHolder>(R.layout.item_menu, data) {

    override fun convert(helper: BaseViewHolder, item: HomeMenuBean) {
        helper.setText(R.id.tv_menu_name, item.name)
    }
}