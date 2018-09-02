package com.DesignPattern.Factory.Strategy;

/**
 * Name: SubWayStrategy
 * Author: tunsh
 * Date: 2018-04-24 17:43
 */
public class SubWayStrategy implements Caculation {
    /*
    * 6公里3万，6-12  4元
    * */
    @Override
    public int caculationPrice(int km) {
        if(km<=6){
            return 3;
        }else if(km>6&&km<=12){
            return 4;
        }else {
            return 6;
        }
    }
}
