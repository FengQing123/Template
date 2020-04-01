package com.example.template.jetpack.room.viewmode_room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * 功能描述：
 * Created by gfq on 2020/4/1.
 */
@Database(entities = {Course.class}, version = 1)
public abstract class CourseDataBase extends RoomDatabase {

    public abstract CourseDao mCourseDao();

    private static CourseDataBase instance;

    public static synchronized CourseDataBase getInstance(Context mContext) {
        if (instance == null) {
            synchronized (CourseDataBase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(mContext.getApplicationContext(), CourseDataBase.class, "course.db")
                            .build();
                }
            }
        }
        return instance;
    }
}
