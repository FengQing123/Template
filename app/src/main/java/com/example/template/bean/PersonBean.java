package com.example.template.bean;

/**
 * 功能描述：
 * Created by gfq on 2019/12/20.
 */
public class PersonBean {

    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private int age;


    public PersonBean(String name) {
        this.name = name;
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
}
