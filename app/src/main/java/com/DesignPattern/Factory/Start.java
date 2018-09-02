package com.DesignPattern.Factory;

/**
 * Name: Start
 * Author: tunsh
 * Date: 2018-04-24 14:24
 */
public class Start {
    /*
    * 工厂模式测试
    * */
    public void ceShi(){
        ComputerFactory factory = new LegendComputer();
        LegendPad legendPad = factory.creatComputer(LegendPad.class);
    }
}
