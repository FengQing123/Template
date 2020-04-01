package com.example.template.jetpack.room.viewmode_room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * 功能描述：
 * Created by gfq on 2020/4/1.
 */
public class CourseViewModel extends AndroidViewModel {

    public int i;

    private CourseRepository courseRepository;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        courseRepository = new CourseRepository(application);
    }

    public void insert(Course... courses) {
        courseRepository.insert(courses);
    }

    public void update(Course... courses) {
        courseRepository.update(courses);
    }

    public LiveData<List<Course>> getAllCourse() {
        return courseRepository.getAllCourse();
    }
}
