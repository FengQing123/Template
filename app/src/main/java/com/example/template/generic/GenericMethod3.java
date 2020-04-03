package com.example.template.generic;

import androidx.annotation.NonNull;

/**
 * 功能描述：泛型方法
 * Created by gfq on 2020/4/2.
 */
public class GenericMethod3 {

    static class Fruit {
        @NonNull
        @Override
        public String toString() {
            return "fruit";
        }
    }

    static class Apple extends Fruit {
        @NonNull
        @Override
        public String toString() {
            return "apple";
        }
    }

    static class Person {
        @NonNull
        @Override
        public String toString() {
            return "person";
        }
    }

    static class GenericTest<T> {

        //使用类声明的泛型
        public void show_1(T t) {
            System.out.println(t.toString());
        }

        /**
         * 在泛型类中声明一个泛型方法，使用泛型 T
         * 需要注意的是：这个 T 是一种全新的类型，可以与类声明的 T 不是同一种类型
         *
         * @param t
         * @param <T>
         */
        public <T> void show_2(T t) {
            System.out.println(t.toString());
        }

        /**
         * 在泛型类中声明一个泛型方法，使用泛型 E ,这种泛型 E 可以为任意类型
         * 可以和类声明 T 相同，也可以不同
         * 由于泛型方法在声明的时候会声明泛型 E ,所以即使在泛型类中未声明泛型 E ，编译器也能够正确识别
         *
         * @param t
         * @param <E>
         */
        public <E> void show_3(E t) {
            System.out.println(t.toString());
        }
    }

    public static void main(String[] args) {
        Apple apple = new Apple();
        Person person = new Person();

        GenericTest<Fruit> genericTest = new GenericTest<>();

        genericTest.show_1(apple); //因为泛型 T->Fruit, Apple是Fruit的子类，编译可以通过
//        genericTest.show_1(person);//show_1(T t)方法使用的是类声明的泛型，所以编译不通过；

        //因为show_2()是重新声明的泛型 T，和类的泛型无关，所以Apple 和 Person 都可以编译通过
        genericTest.show_2(apple);
        genericTest.show_2(person);

        //因为show_3()是重新声明的泛型 E，和类的泛型无关，所以Apple 和 Person 都可以编译通过
        genericTest.show_3(apple);
        genericTest.show_3(person);
    }
}
