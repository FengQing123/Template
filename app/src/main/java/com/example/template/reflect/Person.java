package com.example.template.reflect;

/**
 * 功能描述：
 * Created by gfq on 2020/3/30.
 */
public class Person {
    String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        System.out.println("执行方法，获取年龄=" + age);
        return age;
    }

    public void setAge(int age) {
        System.out.println("执行方法，设置年龄=" + age);
        this.age = age;
    }

    //带参数的构造器
    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public Person() {
        super();
    }

    //私有方法
    private void privateMethod() {
        System.out.println("this is private method!!");
    }
}
