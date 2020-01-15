package com.example.mvp2.course.fragment

import android.view.View
import com.example.mvp2.R
import com.example.mvp2.base.BaseFragment
import com.example.mvp2.course.module.CourseBean
import com.example.mvp2.course.present.CoursePresenter
import com.example.mvp2.course.view.CourseView
import kotlinx.android.synthetic.main.fragment_course.*
import kotlinx.android.synthetic.main.fragment_course.view.*


/**
 * 功能描述：
 * Created by gfq on 2020/1/15.
 */

class CourseFragment : BaseFragment<CourseView, CoursePresenter>(), CourseView {

    override fun initPresenter(): CoursePresenter {
        return CoursePresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_course
    }

    override fun initView(view: View) {
        view.btn_show_data.setOnClickListener {
            mPresenter?.loadData()
        }
    }

    override fun initData() {

    }

    override fun onDataLoaded(courseBean: CourseBean) {
        tv_show_data.text = courseBean.name
    }

}