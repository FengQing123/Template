package com.example.template.proxy;

import com.example.template.proxy.dynamic.MarkCompany;

/**
 * 功能描述：
 * Created by gfq on 2020/3/30.
 */
public class Client {

    public static void main(String[] args) {
        //静态代理模式
//        ManToolsFactory factory = new AFactory();
//        ProxyPerson proxyPerson = new ProxyPerson(factory);
//        proxyPerson.saleManTools("D");
//
//        WomanToolsFactory factory1 = new BFactory();
//        ProxyPersonTwo proxyPersonTwo = new ProxyPersonTwo(factory1);
//        proxyPersonTwo.saleWomanTools(12f);


        /**
         * 动态代理
         */
        MarkCompany company = new MarkCompany();

        //张三来了
        ManToolsFactory aFactory = new AFactory();
        company.setFactory(aFactory);
        ManToolsFactory employee1 = (ManToolsFactory) company.getProxyInstance();
        employee1.saleManTools("E");

        //李四来了
        WomanToolsFactory bFactory = new BFactory();
        company.setFactory(bFactory);
        WomanToolsFactory employee2 = (WomanToolsFactory) company.getProxyInstance();
        employee2.saleWomanTools(13f);

    }
}
