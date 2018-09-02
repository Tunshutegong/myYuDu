package com.DesignPattern.Factory.Strategy;

/**
 * Name: BusStrategy
 * Author: tunsh
 * Date: 2018-04-24 17:31
 */
public class BusStrategy implements Caculation {
    /*
    * 10公里内一元，没增加5公里增加一元。
    * */
    @Override
    public int caculationPrice(int km) {
        int extra = km - 10;
        if(extra<=0){
            return 1;
        }else {
            int bei = extra/5;
            return bei*1+1;
        }
    }
}
