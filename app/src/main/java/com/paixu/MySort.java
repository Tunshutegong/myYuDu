package com.paixu;

import android.util.Log;

import java.util.Arrays;

/**
 * Name: MySort
 * Author: tunsh
 * Date: 2018-04-03 16:15
 * Integer[] nums = {6,1,2,7,9,3,4,5,10,8};//需要排序的数组
 */
public class MySort {
    public static int erFen(int[] arr,int key){
        int low = 0;
        int hight = arr.length-1;
        int mid = 0;
        if(arr[low]>key||key>arr[hight]||low>hight){
            return -1;
        }
        while (low<=hight){
            mid = (low+hight)/2;
            if(arr[mid]<key){
                low = mid+1;
            }else if(arr[mid]>key){
                hight = mid-1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
