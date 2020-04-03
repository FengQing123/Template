package com.example.template.generic;

/**
 * 功能描述：泛型和异常
 * Created by gfq on 2020/4/2.
 */
public class RestrictException {

    //泛型类不能 extends Exception/Throwable
//    private  class Problem<T> extends Exception{}


    //不能捕获泛型类对象
//    public<T extends Throwable> void doWork(T x){
//        try {
//
//        }catch (T x){
//
//        }
//    }

    //这样就可以
    public<T extends Throwable> void doWorkSuccess(T x) throws T{
        try {

        }catch (Throwable e){
            throw  x;
        }
    }

}
