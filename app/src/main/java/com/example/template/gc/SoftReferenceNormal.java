package com.example.template.gc;

import java.lang.ref.SoftReference;

/**
 * 功能描述：
 * Created by gfq on 2020/4/14.
 */
public class SoftReferenceNormal {
    static class SoftObject {
        byte[] data = new byte[120 * 1024 * 1024];
    }

    public static void main(String[] args) {
        SoftReference<SoftObject> s = new SoftReference<>(new SoftObject());

        System.out.println("111 =" + s.get());

        System.out.println("222 =" + s.get());

        SoftObject object = new SoftObject();

        System.out.println("333 = " + s.get());

    }
}
