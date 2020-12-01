package com.example.appkotlin.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * 功能描述：
 * Created by gfq on 2020/12/1
 *
 **/

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mActivity: BaseActivity

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mActivity = this
    }
}