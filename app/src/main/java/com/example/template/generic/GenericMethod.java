package com.example.template.generic;

/**
 * 功能描述：泛型方法
 * Created by gfq on 2020/4/2.
 */
public class GenericMethod {

    public <T> T genericMethod(T... a) {
        return a[a.length / 2];
    }

    public void test(int x, int y) {
        System.out.println(x + y);
    }

    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();
        genericMethod.test(2, 1);
        System.out.println(genericMethod.<String>genericMethod("Android", "Java", "Kotlin"));
        System.out.println(genericMethod.genericMethod(1, 2, 3));
    }

}
