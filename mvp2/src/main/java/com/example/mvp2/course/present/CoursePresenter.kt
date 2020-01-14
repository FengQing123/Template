package com.example.mvp2.course.present

import com.example.mvp2.base.BasePresenter
import com.example.mvp2.course.module.CourseBean
import com.example.mvp2.course.view.CourseView


/**
 * 功能描述：
 * Created by gfq on 2020/1/14.
 */

class CoursePresenter : BasePresenter<CourseView>() {

    fun loadData() {

        val courseBean = CourseBean("Android")

        getBaseView()?.onDataLoaded(courseBean)
    }
}