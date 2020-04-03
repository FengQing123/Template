package com.example.template.generic.module;

/**
 * 功能描述：通配符的使用
 * extends 可以安全的读取数据 getData
 * super 可以安全的写入数据 setData
 * Created by gfq on 2020/4/2.
 */
public class WildChar {

    public static void print(GenericType<Fruit> p) {
        System.out.println(p.getData().getColor());
    }

    /**
     * 上界只能是Fruit,安全的访问数据
     *
     * @param p
     */
    public static void print2(GenericType<? extends Fruit> p) {
        System.out.println(p.getData().getColor());
    }

    public static void use() {
        GenericType<Fruit> a = new GenericType<>();
        //编译通过：print(GenericType<Fruit> p)
        print(a);

        GenericType<Orange> b = new GenericType<>();
        //编译不通过，因为GenericType<Orange> 和  GenericType<Fruit>没有继承关系
//        print(b);

        //编译通过：print2(GenericType<? extends Fruit> p)
        //传入的是 GenericType对象，其中 Fruit 和 Orange 是 Fruit的派生类
        print2(a);
        print2(b);

        GenericType<Food> food = new GenericType<>();
        //编译不通过：print2(GenericType<? extends Fruit> p)
        // 因为上界只能是Fruit
//        print2(food);

        GenericType<? extends Fruit> c = new GenericType<>();
        GenericType<Apple> appleGenericType = new GenericType<>();
        //以下编译都不通过,搞不懂
//        c.setData(new Apple());
//        c.setData(new Fruit());
//        c.setData(appleGenericType);

        //extends 可以安全的访问数据
        Fruit x = c.getData();

    }


    public static void printSuper(GenericType<? super Apple> p) {
        System.out.println(p.getData());
    }

    public static void useSuper() {
        GenericType<Fruit> fruitGenericType = new GenericType<>();
        GenericType<Apple> appleGenericType = new GenericType<>();
        GenericType<Orange> orangeGenericType = new GenericType<>();
        GenericType<HongHuShi> hongHuShiGenericType = new GenericType<>();

        printSuper(fruitGenericType);
        printSuper(appleGenericType);
        //编译不通过
//        printSuper(orangeGenericType);
//        printSuper(hongHuShiGenericType);

        GenericType<? super Apple> x = new GenericType<>();

        //编译通过：<? super Apple> 可以安全的写入数据
        //HongHuShi、Apple可以安全转型为Apple，而Fruit不能安全转型，所以setData(new Fruit())编译不通过
        x.setData(new Apple());
        x.setData(new HongHuShi());
        //编译不通过：
//        x.setData(new Fruit());
//        x.setData(new Orange());

        Object apple = x.getData();
    }
}
