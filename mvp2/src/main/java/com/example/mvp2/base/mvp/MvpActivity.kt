package com.example.mvp2.base.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


/**
 * 功能描述：
 * Created by gfq on 2020/1/14.
 */

abstract class MvpActivity<P : MvpPresenter<*>> : AppCompatActivity(), MvpView {

    var presenter: P? = null

    /**
     * 获取布局资源文件
     */
    protected abstract fun getLayoutId(): Int

    /**
     * 初始化presenter
     */
    protected abstract fun initPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayoutId() > 0) {
            setContentView(getLayoutId())
        }

        presenter = initPresenter()
//        presenter?.attach(this)
    }
}