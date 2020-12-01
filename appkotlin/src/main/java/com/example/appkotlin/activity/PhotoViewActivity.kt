package com.example.appkotlin.activity

import android.os.Bundle
import com.example.appkotlin.R
import com.example.appkotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_photoview.*

/**
 *
 * 功能描述：
 * Created by gfq on 2020/12/1
 *
 **/

class PhotoViewActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_photoview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        photo_view.setImageResource(R.drawable.pic_01)
    }
}