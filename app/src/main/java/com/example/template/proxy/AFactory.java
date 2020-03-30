package com.example.template.proxy;

/**
 * 功能描述：定义一个真实用品公司，实现接口
 * Created by gfq on 2020/3/30.
 */
public class AFactory implements ManToolsFactory {
    @Override
    public void saleManTools(String size) {
        System.out.println("按需求定制一个size为 " + size + " 的生活用品");
    }
}
