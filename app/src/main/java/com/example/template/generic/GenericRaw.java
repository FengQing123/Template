package com.example.template.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：JVM 类型擦除 :会把泛型 T -> Object 来处理
 * 可以尝试下把   .java文件  转为  .class文件 会做强制转型
 * Created by gfq on 2020/4/3.
 */
public class GenericRaw<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // E -> ArrayList/ Comparable/Serializable
    class GenericRaw2<E extends ArrayList & Comparable & Serializable> {
        private Comparable data;

        public Comparable getData() {
            return data;
        }

        public void setData(Comparable data) {
            this.data = data;
        }
    }

    class GenericRaw3<T extends ArrayList & Comparable & Serializable> {
        private T data;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        private void test(T t) {
            //强制转型，伪泛型
            // (Comparable) data.compareTo(t);
        }
    }

    /**
     * 以下两个方法：(这里还需要复习下)
     * 编译器:只要觉得 方法名 和 参数 一样 就是两个相同的方法
     * JDK :
     * @param strings
     */
//    public static void method(List<String> strings) {
//
//    }
//
//    public static void method(List<Integer> integers){
//
//    }
}


