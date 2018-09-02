package com.suo;

import android.util.Log;

/**
 * Name: TestSuo
 * Author: tunsh
 * Date: 2018-03-07 16:46
 */
public class TestSuo {
    public  static void JintTai(){
        for(int i=0;i<10;i++){
            Log.e("zmb","-->我是静态锁"+i);
        }

    }
    public synchronized static void JintTai2(){
        for(int i=0;i<10;i++){
            Log.e("zmb","-->我是静态锁2--"+i);
        }

    }
    public synchronized  void FeiJintTai(){
        for(int i=0;i<10;i++){
            Log.e("zmb","-->我是非静态锁"+i);
        }

    }
}
