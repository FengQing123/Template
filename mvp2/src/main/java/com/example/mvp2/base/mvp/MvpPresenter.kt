package com.example.mvp2.base.mvp


/**
 * 功能描述：
 * Created by gfq on 2020/1/14.
 */

abstract class MvpPresenter<V : MvpView> {

    private var baseView: V? = null

    fun attach(baseView: V) {
        this.baseView = baseView
    }

    fun getBaseView(): V? {
        return baseView
    }
}