package com.example.template.jetpack.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.template.jetpack.room.entity.Student;
import com.example.template.jetpack.room.entity.StudentTuple;

import java.util.List;

/**
 * 功能描述：操作层
 * Created by gfq on 2020/4/1.
 */
@Dao
public interface StudentDao {

    @Insert
    void insert(Student... student);

    @Delete
    void delete(Student... student);

    @Update
    void update(Student... student);

    @Query("select * from student")
    List<Student> findAllStudents();

    //查询一条记录（如果有多条，返回的就是第一条）
    @Query("select * from student where name like :name")
    Student findStudentByName(String name);

    //查询一条记录（如果有多条，返回的就是第一条）
    @Query("select * from student where addressId in(:addressId)")
    Student findStudentByAddress(int addressId);

    //查询对应参数的所有记录
    @Query("select * from student where addressId in(:addressId)")
    List<Student> findAllStudentsByAddress(int addressId);


    /**
     * 只查N个字段
     * 这里因为Student表不止两个字段,
     * 所以得重新新建只含两个字段的表StudentTuple,作为返回对象
     *
     * @return
     */
    @Query("select name, pwd from student")
    List<StudentTuple> findRecord();
}
