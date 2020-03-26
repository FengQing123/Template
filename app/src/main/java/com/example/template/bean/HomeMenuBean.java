package com.example.template.bean;

/**
 * 功能描述：
 * Created by gfq on 2020/3/26.
 */
public class HomeMenuBean {

    private String name;

    private Class clz;

    public HomeMenuBean(String name, Class clz) {
        this.name = name;
        this.clz = clz;
    }

    public String getName() {
        return name;
    }

    public Class getClz() {
        return clz;
    }
}
