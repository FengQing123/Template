package com.example.template.proxy;

/**
 * 功能描述：定义一个代理类，实现接口
 * Created by gfq on 2020/3/30.
 */
public class ProxyPersonTwo implements WomanToolsFactory {

    //包含真实的对象
    private WomanToolsFactory factory;

    public ProxyPersonTwo(WomanToolsFactory factory) {
        this.factory = factory;
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


    @Override
    public void saleWomanTools(float length) {
        /**
         * 使用代理类，增加其他功能
         * 并使用真实对象来生产
         */
        doSthBefore();
        factory.saleWomanTools(length);
        doSthAfter();
    }
}
