package com.DesignPattern.Factory;

/**
 * Name: LegendComputer
 * Author: tunsh
 * Date: 2018-04-24 14:17
 */
public class LegendComputer extends ComputerFactory {
    @Override
    public <T extends MyCumputer> T creatComputer(Class<T> tClass) {
        MyCumputer myCumputer = null;
        try {
            myCumputer = (MyCumputer)Class.forName(tClass.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T)myCumputer;
    }
}
