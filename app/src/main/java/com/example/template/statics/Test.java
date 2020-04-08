package com.example.template.statics;

/**
 * 功能描述：
 * Created by gfq on 2020/4/3.
 */
public class Test {

    Person person = new Person("Test");

    static {
        System.out.println("Test static block");
    }

    {
        System.out.println("Test block");
    }

    public Test() {
        System.out.println("Test Constructor");
    }

    public static void main(String[] args) {
        new MyClass();
    }

}

class Person {
    static {
        System.out.println("Person static block");
    }

    {
        System.out.println("Person block");
    }

    public Person(String str) {
        System.out.println("Person Constructor " + str);
    }
}

class MyClass extends Test {
    Person person = new Person("MyClass");

    static {
        System.out.println("MyClass static block");
    }

    {
        System.out.println("MyClass block");
    }

    public MyClass() {
        System.out.println("MyClass Constructor");
    }
}
