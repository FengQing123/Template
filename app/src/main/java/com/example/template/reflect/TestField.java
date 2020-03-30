package com.example.template.reflect;

import java.lang.reflect.Field;

/**
 * 功能描述：反射 获取属性
 * Created by gfq on 2020/3/30.
 */
public class TestField {

    public static void testField() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class clazz = Class.forName("com.example.template.reflect.Person");

        System.out.println("获取公有和私有的所有字段，但不能获取父类的字段");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }


        System.out.println("获取指定的字段");
        Field field = clazz.getDeclaredField("name");
        System.out.println(field.getName());


        System.out.println("获取指定字段的值");
        Person person = new Person("Android", 10);
        Object val = field.get(person);
        System.out.println(field.getName() + "=" + val);


        System.out.println("设置指定对象指定字段的值");
        field.set(person, "Java");
        System.out.println(field.getName() + "=" + person.getName());


        System.out.println("字段是私有的，不管是读值还是写值，都必须先调用setAccessible(true)方法");
        field = clazz.getDeclaredField("age");
        field.setAccessible(true);
        val = field.get(person);
        System.out.println(field.getName() + "=" + val);

    }
}
