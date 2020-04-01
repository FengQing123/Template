package com.example.template.jetpack.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 功能描述：
 * Created by gfq on 2020/4/1.
 */
@Entity
public class Person {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @Embedded
    private Address address;


}
