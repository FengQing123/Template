package com.example.template.statics;

/**
 * 功能描述：
 * Created by gfq on 2020/4/3.
 */
public class Dog extends Animal {

    static {
        System.out.println("Dog static Block");
    }

    {
        System.out.println("Dog Block");
    }

    public Dog() {
        System.out.println("Dog constructor");
    }

    public static void main(String[] args) {
        new Dog();
    }
}

class Animal {

    static {
        System.out.println("Animal static Block");
    }

    {
        System.out.println("Animal Block");
    }

    public Animal() {
        System.out.println("Animal constructor");
    }
}
