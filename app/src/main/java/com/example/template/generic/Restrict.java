package com.example.template.generic;

/**
 * 功能描述：泛型的约束和局限性
 * Created by gfq on 2020/4/2.
 */
public class Restrict<T> {

    private T data;

    //不能实例化类型变量
//    public Restrict() {
//        this.data = new T();
//    }

    //静态域或者 静态方法 不能引用类型变量
    //因为静态域早于构造函数
//    private static T instance;
//    private static T getInstance() {}

    //静态方法 本身是类型方法就行
//    private static <T> T getInstance() {}


    public static void main(String[] args) {
        //基本类型不行
//        Restrict<double> d=new Restrict<double>();
        //使用double 的 封装类型Double
        Restrict<Double> dd = new Restrict<>();

        //不支持 instanceof
//        if(dd instanceof Restrict<Double>){}
//        if(dd instanceof Restrict<T>){}


        Restrict<Integer> restrictInt = new Restrict<>();
        Restrict<String> restrictString = new Restrict<>();

        System.out.println(restrictInt.getClass() == restrictString.getClass());//true
        System.out.println(restrictInt.getClass().getName());//com.example.template.generic.Restrict
        System.out.println(restrictString.getClass().getName());//com.example.template.generic.Restrict


        //这样不行
//        Restrict<Double> [] restricts =new Restrict<Double>[10];
        //这样就可以
        Restrict<Double> [] restrictss =new Restrict[10];
    }
}
