package com.example.template.proxy;

/**
 * 功能描述：
 * Created by gfq on 2020/3/30.
 */
public class BFactory implements WomanToolsFactory {
    @Override
    public void saleWomanTools(float length) {
        System.out.println("按需求定制一个length为 " + length + " 的生活用品");
    }
}
