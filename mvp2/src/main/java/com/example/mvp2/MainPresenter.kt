package com.example.mvp2


/**
 * 功能描述：
 * Created by gfq on 2020/1/14.
 */

class MainPresenter(private val mView: MainContract.View) : MainContract.Presenter {

    override fun loadData() {
        val s = "我是从服务器获取的数据"
        mView.onDataLoaded(s)
    }

}