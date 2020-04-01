package com.example.template.jetpack.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

/**
 * 功能描述：
 * Created by gfq on 2020/4/1.
 */
@Entity
public class StudentTuple {

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "pwd")
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "StudentTuple{" +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
