package com.DesignPattern.Factory.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Name: Test
 * Author: tunsh
 * Date: 2018-04-25 15:02
 */
public class Test  {
    public void ceshi(){
        RealBuy realBuy = new RealBuy();
        DyProxy dyProxy = new DyProxy();
        dyProxy.setObj(realBuy);
        ClassLoader loader = dyProxy.getClass().getClassLoader();
        Subject pb = (Subject) Proxy.newProxyInstance(loader,new Class[]{Subject.class},dyProxy);
        pb.visit();

    }


}
