package com.example.template.casting;

/**
 * 功能描述：Java 向上转型 ： 把子类对象直接赋给父类引用 ，不需要强制转换 ：
 * Father f1 = new Son();
 * Created by gfq on 2020/4/3.
 */
public class UpCastingTest {

    /**
     * 向上转型的作用：以Animal 为参数，体现了Java的抽象编程思想
     *
     * @param animal
     */
    public static void doEat(Animal animal) {
        animal.eat();
    }

    public static void main(String[] args) {
        Animal animal = new Bird();
        animal.eat();//animal 实际指向的是 Bird 子类，故调用时会调用子类本身的方法
//        animal.fly();//向上转型时 animal 会遗失除与父类对象共有的其他方法.因为父类没有fly,所以编译不通过

        Animal a = new Animal();
        Bird b = new Bird();
        doEat(a);//输出：Animal eat
        doEat(b);//输出：Bird eat
    }
}

class Animal {
    public void eat() {
        System.out.println("Animal eat");
    }
}

class Bird extends Animal {
    public void eat() {
        System.out.println("Bird eat");
    }

    public void fly() {
        System.out.println("bird fly");
    }
}
