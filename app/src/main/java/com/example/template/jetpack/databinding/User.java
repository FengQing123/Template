package com.example.template.jetpack.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.template.BR;

/**
 * 功能描述：
 * Created by gfq on 2020/3/31.
 */
public class User extends BaseObservable {

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
