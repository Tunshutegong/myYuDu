package com.DesignPattern.Factory.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Name: DyProxy
 * Author: tunsh
 * Date: 2018-04-25 15:24
 */
public class DyProxy implements InvocationHandler {
    private Object obj;

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(obj,args);
        return result;
    }
}
