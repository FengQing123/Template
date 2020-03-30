package com.example.template.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 功能描述：反射 获取方法
 * Created by gfq on 2020/3/30.
 */
public class TestMethod {
    public static void testMethod() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        Class clazz = Class.forName("com.example.template.reflect.Person");

        System.out.println("获取clazz对应类中的所有方法,"
                + "不会获取private方法，且获取从父类继承来的所有方法-------------");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + "()");
        }


        System.out.println("获取clazz对应类中的所有方法,"
                + "包括private方法，且只获取当前类的方法-------------");
        methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + "()");
        }


        System.out.println("获取clazz对应类中的指定方法，"
                + "需要参数名称和参数列表，无参数则不需要写-------------");
        Method method = clazz.getDeclaredMethod("setName", String.class);
        System.out.println(method);

        method = clazz.getDeclaredMethod("setAge", int.class);
        System.out.println(method);


        System.out.println("执行方法，第一个参数表示执行哪个对象的方法，"
                + "剩下的参数是执行方法时需要传入的参数-------------");
        Object obj = clazz.newInstance();
        method.invoke(obj, 18);
        method = clazz.getDeclaredMethod("getAge");
        method.invoke(obj);


        System.out.println("私有方法的执行,"
                + "调用invoke之前需加上一句method.setAccessible(true)-------------");
        method = clazz.getDeclaredMethod("privateMethod");
        method.setAccessible(true);
        method.invoke(obj);

    }
}
