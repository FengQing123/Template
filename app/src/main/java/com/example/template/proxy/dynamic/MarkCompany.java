package com.example.template.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 功能描述：使用Proxy实现动态代理
 * Created by gfq on 2020/3/30.
 */
public class MarkCompany implements InvocationHandler {

    //持有的真实对象
    private Object factory;

    public void setFactory(Object factory) {
        this.factory = factory;
    }

    /**
     * 通过Proxy获得动态代理对象
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(factory.getClass().getClassLoader(),
                factory.getClass().getInterfaces(), this);
    }


    /**
     * 通过动态代理对象方法进行增强
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        doSthBefore();
        Object obj = method.invoke(factory, args);
        doSthAfter();

        return obj;
    }

    /**
     * 前置处理
     */
    private void doSthBefore() {
        System.out.println("精美包装，快递一条龙服务");
    }

    /**
     * 后置处理
     */
    private void doSthAfter() {
        System.out.println("根据需求，进行市场调研");
    }

}
