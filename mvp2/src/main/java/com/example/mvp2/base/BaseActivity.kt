package com.example.mvp2.base

import com.example.mvp2.base.mvp.MvpActivity

/**
 * 功能描述：
 * Created by gfq on 2020/1/14.
 */

abstract class BaseActivity<V : BaseView, P : BasePresenter<V>> : MvpActivity<V, P>() {

}