package com.diy;

/**
 * Name: d
 * Author: tunsh
 * Date: 2018-03-06 15:21
 */
public interface  DIYMap<K,V> {
    //Map双列集合 基本功能是 快速取
    public V put(K k,V v);
    //快速取
    public V get(K k);

    //定义一个内部接口
    public interface Entry<K,V>{
        public K getKey();

        public V getValue();
    }
}