package com.example.template.casting;

/**
 * 功能描述：Java 向下转型 ：把 指向子类对象的父类引用 赋给 子类引用 叫向下转型(downcasting)，要强制转换：
 * Father f1 = new Son();
 * Son s1 = (Son)f1;
 * <p>
 * 向上转型时 animal 会遗失除与父类对象共有的其他方法；可以用向下转型在重新转回，这个和向上转型的作用要结合理解。
 * <p>
 * <p>
 * https://bbs.huaweicloud.com/blogs/122508
 * Created by gfq on 2020/4/3.
 */
public class DownCasting {

    public static void main(String[] args) {
        Fruit a1 = new Apple();
        Fruit a2 = new Fruit();

        Apple b1 = (Apple) a1;// 向下转型,编译和运行皆不会出错
//        Apple b2 = (Apple) a2;//不安全的向下转型,编译无错但会运行会出错

        a1.eat();
        a2.eat();
        b1.eat();
    }
}

class Fruit {
    private String name = "Fruit";

    public void eat() {
        System.out.println("eat:" + name);
    }
}

class Apple extends Fruit {
    private String name = "Apple";

    public void eat() {
        System.out.println("eat:" + name);
    }
}