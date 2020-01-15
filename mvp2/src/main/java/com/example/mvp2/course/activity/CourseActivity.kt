package com.example.mvp2.course.activity

import androidx.fragment.app.FragmentTransaction
import com.example.mvp2.R
import com.example.mvp2.base.BaseActivity
import com.example.mvp2.course.fragment.CourseFragment
import com.example.mvp2.course.module.CourseBean
import com.example.mvp2.course.present.CoursePresenter
import com.example.mvp2.course.view.CourseView
import kotlinx.android.synthetic.main.activity_main.*


/**
 * 功能描述：
 * Created by gfq on 2020/1/14.
 */

class CourseActivity : BaseActivity<CourseView, CoursePresenter>(), CourseView {

    override fun getLayoutId(): Int {
        return R.layout.activity_course
    }

    override fun initPresenter(): CoursePresenter {
        return CoursePresenter()
    }

    override fun initView() {
        btn_get_data.setOnClickListener {
            mPresenter?.loadData()
        }
    }

    override fun initData() {

    }

    override fun onDataLoaded(courseBean: CourseBean) {
        tv_data.text = courseBean.name
    }
}