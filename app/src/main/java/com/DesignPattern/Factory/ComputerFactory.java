package com.DesignPattern.Factory;

/**
 * Name: ComputerFactory
 * Author: tunsh
 * Date: 2018-04-24 14:14
 */
public abstract class ComputerFactory {
    public abstract <T extends MyCumputer> T creatComputer(Class<T> tClass);
}
