package com.DesignPattern.Factory.Strategy;

/**
 * Name: TranPrice
 * Author: tunsh
 * Date: 2018-04-24 18:26
 */
public class TranPrice {
    
    private Caculation caculation;

    public void setCaculation(Caculation caculation) {
        this.caculation = caculation;
    }

    public int tranPrice(int km){
        return caculation.caculationPrice(km);
    }
}
