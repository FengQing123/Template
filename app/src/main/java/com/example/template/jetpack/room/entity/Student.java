package com.example.template.jetpack.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * 功能描述：学生表
 * Created by gfq on 2020/4/1.
 */
@Entity
public class Student {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "pwd")
    private String password;

    @ColumnInfo(name = "addressId")
    private int addressId;

    @ColumnInfo(name = "age")
    private int age;


    @Ignore
    public Student(String name, String password, int addressId) {
        this.name = name;
        this.password = password;
        this.addressId = addressId;
    }

    public Student(String name, String password, int addressId, int age) {
        this.name = name;
        this.password = password;
        this.addressId = addressId;
        this.age = age;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", addressId=" + addressId +
                '}';
    }
}
