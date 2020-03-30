package com.example.template.reflect;

/**
 * 功能描述：反射 获取类
 * Created by gfq on 2020/3/30.
 */
public class TestClass {
    public static void testClass() throws ClassNotFoundException {
        Person person = new Person();
        person.setName("Android");

        Class clz = Person.class;
        Class clz2 = person.getClass();
        Class clz3 = Class.forName("com.example.template.reflect.Person");
    }
}
