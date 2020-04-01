package com.example.template.jetpack.room;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.template.R;
import com.example.template.app.BaseActivity;
import com.example.template.jetpack.room.dao.AddressDao;
import com.example.template.jetpack.room.dao.StudentDao;
import com.example.template.jetpack.room.database.AppDataBase;
import com.example.template.jetpack.room.entity.Address;
import com.example.template.jetpack.room.entity.Student;
import com.example.template.jetpack.room.entity.StudentTuple;

import java.util.List;

/**
 * 功能描述：
 * Created by gfq on 2020/4/1.
 */
public class RoomTestActivity extends BaseActivity {


    private static final String TAG = "RoomTestActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_room;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



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
