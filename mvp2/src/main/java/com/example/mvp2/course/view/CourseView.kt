package com.example.mvp2.course.view

import com.example.mvp2.base.BaseView
import com.example.mvp2.course.module.CourseBean


/**
 * 功能描述：
 * Created by gfq on 2020/1/14.
 */

interface CourseView : BaseView {
    fun onDataLoaded(courseBean: CourseBean)
}