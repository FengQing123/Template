package com.example.template.jetpack.room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.R;
import com.example.template.app.BaseActivity;
import com.example.template.jetpack.room.dao.AddressDao;
import com.example.template.jetpack.room.dao.StudentDao;
import com.example.template.jetpack.room.database.AppDataBase;
import com.example.template.jetpack.room.entity.Address;
import com.example.template.jetpack.room.entity.Student;
import com.example.template.jetpack.room.entity.StudentTuple;
import com.example.template.jetpack.room.viewmode_room.Course;
import com.example.template.jetpack.room.viewmode_room.CourseAdapter;
import com.example.template.jetpack.room.viewmode_room.CourseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 * Created by gfq on 2020/4/1.
 */
public class RoomTestActivity extends BaseActivity {

    private static final String TAG = "RoomTestActivity";

    private CourseViewModel mCourseViewModel;

    private RecyclerView mRecycleView;

    private CourseAdapter courseAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_room;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecycleView = findViewById(R.id.recycleview);
        mRecycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        courseAdapter = new CourseAdapter(new ArrayList<>());
        mRecycleView.setAdapter(courseAdapter);

        mCourseViewModel = ViewModelProviders.of(mActivity).get(CourseViewModel.class);
        mCourseViewModel.getAllCourse().observe(mActivity, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                courseAdapter.setNewData(courses);
            }
        });

        findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 50; i++) {
                            mCourseViewModel.insert(new Course("Andorid" + i, "10" + i));
                        }
                    }
                }).start();

            }
        });

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCourseViewModel.getAllCourse().observe(mActivity, new Observer<List<Course>>() {
                    @Override
                    public void onChanged(List<Course> courses) {
                        courseAdapter.setNewData(courses);
                    }
                });
            }
        });

        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(2000);
                                int flag = ++mCourseViewModel.i;
                                mCourseViewModel.update(new Course(5, "Java", "1" + flag));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });

    }


    private void useViewModelUpdateUI() {


    }


    private void testRoom() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                //数据库的操作应该在子线程
                StudentDao dao = AppDataBase.getInstance(mActivity).studentDao();

                AddressDao addressDao = AppDataBase.getInstance(mActivity).addressDao();

//                addressDao.insert(new Address(1, "东方"));
//                addressDao.insert(new Address(2, "西方"));
//                addressDao.insert(new Address(3, "南方"));
//                addressDao.insert(new Address(4, "北方"));

//                dao.insert(new Student("Android", "123343", 1, 12));
//                dao.insert(new Student("Android", "123343", 2, 10));
//                dao.insert(new Student("Android", "123343", 3, 13));
//                dao.insert(new Student("Android", "123343", 4, 14));


                List<Address> addresses = addressDao.findAllAddress();
                Log.e(TAG, "所有地址：" + addresses);

                List<Student> students = dao.findAllStudents();
                Log.e(TAG, "所以学生：" + students.toString());

                Student student3 = dao.findStudentByName("Android");
                Log.e(TAG, "name= Android 的学生：" + student3);

                Student student2 = dao.findStudentByAddress(3);
                Log.e(TAG, "addressId= 3 的学生：" + student2);

                List<Student> student1 = dao.findAllStudentsByAddress(3);
                Log.e(TAG, "addressId= 3 的所有学生：" + student1);

                List<StudentTuple> s4 = dao.findRecord();
                Log.e(TAG, "只查询name 和psw 的所有学生：" + s4);
            }
        }).start();
    }


}
