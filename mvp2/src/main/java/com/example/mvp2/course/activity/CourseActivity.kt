package com.example.mvp2.course.activity

import android.os.Bundle
import com.example.mvp2.base.BaseActivity
import com.example.mvp2.course.present.CoursePresenter


/**
 * 功能描述：
 * Created by gfq on 2020/1/14.
 */

class CourseActivity : BaseActivity<CoursePresenter>() {

    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initPresenter(): CoursePresenter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}