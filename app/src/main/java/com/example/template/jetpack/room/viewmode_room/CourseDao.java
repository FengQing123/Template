package com.example.template.jetpack.room.viewmode_room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * 功能描述：
 * Created by gfq on 2020/4/1.
 */
@Dao
public interface CourseDao {

    @Insert
    void insert(Course... courses);

    @Update
    void update(Course... courses);

    @Query("select * from course")
    LiveData<List<Course>> getAllCourse();

}
