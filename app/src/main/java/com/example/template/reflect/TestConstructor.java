package com.example.template.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 功能描述：反射 获取构造函数
 * Created by gfq on 2020/3/30.
 */
public class TestConstructor {

    public static void testConstructor() throws ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        String className = "com.example.template.reflect.Person";
        Class<Person> clazz = (Class<Person>) Class.forName(className);

        System.out.println("获取全部的Constructor对象-------");
        Constructor<Person>[] constructors = (Constructor<Person>[]) clazz.getConstructors();
        for (Constructor<Person> constructor : constructors) {
            System.out.println(constructor);
        }


        System.out.println("获取某一个Constructor对象，需要参数列表-------");
        Constructor<Person> constructor = clazz.getConstructor(String.class, int.class);
        System.out.println(constructor);


        System.out.println("调用构造器的 newInstance() 方法创建对象-------");
        Person obj = constructor.newInstance("Android", 10);
        System.out.println(obj.getName());
    }
}
