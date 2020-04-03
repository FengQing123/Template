package com.example.template.generic.module;

/**
 * 功能描述：
 * Created by gfq on 2020/4/2.
 */
public class GenericType<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
