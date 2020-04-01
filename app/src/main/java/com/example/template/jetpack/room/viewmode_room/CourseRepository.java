package com.example.template.jetpack.room.viewmode_room;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * 功能描述：
 * Created by gfq on 2020/4/1.
 */
public class CourseRepository {

    private CourseDao courseDao;

    public CourseRepository(Context mContext) {
        courseDao = CourseDataBase.getInstance(mContext).mCourseDao();
    }

    //提供其他API

    //插入
    void insert(Course... courses) {
        courseDao.insert(courses);
    }

    void update(Course... courses) {
        courseDao.update(courses);
    }

    LiveData<List<Course>> getAllCourse() {
        return courseDao.getAllCourse();
    }

}
