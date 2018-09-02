package com.paixu;

import android.view.LayoutInflater;

/**
 * Name: DanLi
 * Author: tunsh
 * Date: 2018-04-10 17:15
 */
public class DanLi {
    private static DanLi danLi = null;
    private DanLi(){
    }
    public static DanLi getDanLi(){
        if(danLi == null){
            synchronized (DanLi.class){
                if(danLi == null){
                    danLi = new DanLi();
                }
            }
        }
        return danLi;
    }
}
