package com;

import android.util.Log;

import java.util.Stack;

/**
 * Name: paixu
 * Author: tunsh
 * Date: 2018-04-02 17:14
 */
public class PaiXu {
    /*
    * 快速排序
    * Integer[] nums = {10,58,72,5,9,7,45,15};//需要排序的数组
    * 7 58 72 5 9 10 45 15
    * */
/*
* 快速排序 递归
* */
    public static void sort(Integer[] nums, Integer left, Integer right) {
        int i, j, t, temp;
        if (left > right) {
            return ;
        } else {
            //获取基准数
            temp = nums[left];
            i = left;
            j = right;//{6,1,2,7,9}
            while (i < j) {
                //从右边往左遍历,获取第一个小于基准数的下标
                while (nums[j] >= temp && i < j) {
                    j--;// j==2
                }
                //从左往右遍历，获取第一个大于基准数的下标
                while (nums[i] <= temp && i < j) {
                    i++;//  i==2
                }
                //然后交换两个数在数组中的位置
                if (i < j) {//判断两数下标有没有相遇
                    //若没有相遇
                    //交换两数的位置
                    t = nums[i];
                    nums[i] = nums[j];
                    nums[j] = t;
                }
            }
            nums[left] = nums[i];
            nums[i] = temp;
            sort(nums,left,j-1);
            sort(nums,j+1,right);
        }

    }
/*
* 快速排序非递归  栈
* */
    public static int[] quickSort_not_recursion(int[] result) {
        int i;
        int j;
        int min;    // Every loop's max index
        int max;    // Every loop's minus index
        int key;
        // Record the minus index and the max index
        Stack<Integer> conditions = new Stack<Integer>();
        conditions.push(0);
        conditions.push(result.length-1);

        int temp;

        // In every loop will get a left index and right index.
        while(!conditions.empty()){//{6,1,2,7,9}
            max = conditions.pop();
            Log.e("zym","=================>"+max);
            min = conditions.pop();
            Log.e("zym","max-----min------->"+max+"----"+min);
            key = result[min];//6
            i = min+1;
            j = max;

            // With this step, the numbers can be divided to 2 sections,
            // the left side is smaller than the key value,
            // the right side is bigger than the key value.
            while(i<j) {
                // Get the number's index which is smaller than key
                while (key < result[j] && i<j) {
                    j--;
                }

                // Get the number's index which is bigger than key
                while (key > result[i] && i<j) {
                    i++;
                }
//                Log.e("zym","----------------->"+j+"--------->"+i);

                // Swap
                temp = result[j];
                result[j] = result[i];
                result[i] = temp;
            }

            // Swap the key and i(or j)
            if(key>result[i]){
                temp = result[min];
                result[min] = result[j];
                result[j] = temp;
            }
            Log.e("zym","----------------->"+j+"--------->"+i);

            // Store the left side minus index and the max index
            if(min<i-1){
                conditions.push(min);
                conditions.push(i-1);
            }

            // Store the right side minus index and the max index
            if(max>i+1){
                conditions.push(i+1);
                conditions.push(max);
            }
        }

        return result;
    }
/*
* 二分查找 循环
* {1,3,5,7,9,11}
* */
    public static int commonBinarySearch(int[] arr,int key){
        int low = 0;
        int high = arr.length - 1;
        int middle = 0;
        if(key < arr[low] || key > arr[high] || low > high){
            return -1;
        }
        while(low <= high){
            middle = (low + high) / 2;
            if(arr[middle] > key){
                //比关键字大则关键字在左区域
                high = middle - 1;
            }else if(arr[middle] < key){
                //比关键字小则关键字在右区域
                low = middle + 1;
            }else{
                return middle;
            }
        }
//最后仍然没有找到，则返回-1
        return -1;
    }
    /*
    * 二分查找 递归
    * */
    public static int recursionBinarySearch(int[] arr,int key,int low,int high){

        if(key < arr[low] || key > arr[high] || low > high){
            return -1;
        }
        int middle = (low + high) / 2;          //初始中间位置
        if(arr[middle] > key){
            //比关键字大则关键字在左区域
            return recursionBinarySearch(arr, key, low, middle - 1);
        }else if(arr[middle] < key){
            //比关键字小则关键字在右区域
            return recursionBinarySearch(arr, key, middle + 1, high);
        }else {
            return middle;
        }

    }

    public static void jiaoJI(int[] arr1,int[] arr2){
        int i = 0,j = 0,k=0;
        while(i<3&&j<3){
            if(arr1[i]<arr2[j]){
                i++;
            }else if(arr1[i]>arr2[j]){
                j++;
            }else {
                k++;
                Log.e("zym","k----------------k---------->"+k);
            }
        }
    }
}

