package com.example.mvp2.base.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


/**
 * 功能描述：
 * Created by gfq on 2020/1/14.
 */

abstract class MvpActivity<V : MvpView, P : MvpPresenter<V>> : AppCompatActivity(), MvpView {

    var mPresenter: P? = null

    /**
     * 获取布局资源文件
     */
    protected abstract fun getLayoutId(): Int

    /**
     * 初始化presenter
     */
    protected abstract fun initPresenter(): P

    /**
     * 初始化控件
     */
    protected abstract fun initView()

    /**
     * 初始化数据
     */
    protected abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayoutId() > 0) {
            setContentView(getLayoutId())
        }

        mPresenter = initPresenter()
        mPresenter?.attach(this as V)

        initView()
        initData()
    }
}