package com.example.mvp2.ui.course.view

import com.example.mvp2.base.BaseView
import com.example.mvp2.ui.course.module.CourseBean


/**
 * 功能描述：
 * Created by gfq on 2020/1/14.
 */

interface CourseView : BaseView {
    fun onDataLoaded(courseBean: CourseBean)
}