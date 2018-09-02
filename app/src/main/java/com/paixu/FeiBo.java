package com.paixu;

import android.util.Log;

/**
 * Name: FeiBo
 * Author: tunsh
 * Date: 2018-04-09 14:26
 * 斐波那契数列0, 1, 1, 2, 3, 5, 8, 13, 21
 */
public class FeiBo {
    public static void fb1() {
        int i = 0;
        int j = 1;
        int k = 1;
        for(int n = 0;n<10;n++){

            k = i+j;
            Log.e("zym","--------------->"+k);
            i = j;
            j = k;
        }
    }
    public static int fb2(int n){
        Log.e("zym","-------------->"+n);
        if(n ==1){
            return 0;
        }
        if(n == 2){
            return 1;
        }
        return fb2(n-1)+fb2(n-2);

    }

}
