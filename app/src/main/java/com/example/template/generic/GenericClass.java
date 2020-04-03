package com.example.template.generic;

/**
 * 功能描述：泛型类 （什么时候用到泛型类？？？）
 * Created by gfq on 2020/4/2.
 */
public class GenericClass<T> {

    private T data;

    public GenericClass(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
