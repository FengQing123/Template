package com.example.mvp2.base

import com.example.mvp2.base.mvp.MvpFragment


/**
 * 功能描述：
 * Created by gfq on 2020/1/15.
 */

abstract class BaseFragment<V : BaseView, P : BasePresenter<V>> : MvpFragment<V, P>() {

}
