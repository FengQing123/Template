package com.example.mvp2.base.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mvp2.base.LazyFragment


/**
 * 功能描述：
 * Created by gfq on 2020/1/14.
 */

abstract class MvpFragment<V : MvpView, P : MvpPresenter<V>> : LazyFragment(), MvpView {

    var mPresenter: P? = null

    /**
     * 初始化presenter
     */
    protected abstract fun initPresenter(): P

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter = initPresenter()
        mPresenter?.attach(this as V)
    }

}