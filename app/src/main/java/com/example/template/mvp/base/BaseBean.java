package com.example.template.mvp.base;

import java.io.Serializable;

/**
 * 功能描述：
 * Created by gfq on 2020/3/9.
 */
public class BaseBean<T> implements Serializable {
    /**
     * data :
     * errorCode : 0
     * errorMsg :
     */
    public T data;
    public int errorCode;
    public String errorMsg;

    public BaseBean(int code, String data) {
        this.errorCode = code;
        this.data = (T) data;
    }
}
