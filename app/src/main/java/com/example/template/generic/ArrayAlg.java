package com.example.template.generic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 功能描述：限定类型变量 （extends 派生）
 * Created by gfq on 2020/4/2.
 */
public class ArrayAlg {

    //因为compareTo是接口Comparable的方法，所以需要限定 T 为 Comparable的派生类
//    public static <T> T min(T a, T b) {
//        if (a.compareTo(b) > 0) {
//            return a;
//        } else {
//            return b;
//        }
//    }

    // 因为compareTo是接口Comparable的方法，所以需要限定 T 为 Comparable的派生类
    public static <T extends Comparable> T min(T a, T b) {
        if (a.compareTo(b) > 0) {
            return a;
        } else {
            return b;
        }
    }


    /**
     * extends 后面可以是 类 或者 接口，
     * 也可以同时有 类和接口，但是类只能有一个，而且要位于最前面，接口可以有多个
     *
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public static <T extends ArrayList & Comparable & Serializable> T min2(T a, T b) {
        if (a.compareTo(b) > 0) {
            return a;
        } else {
            return b;
        }
    }

}
