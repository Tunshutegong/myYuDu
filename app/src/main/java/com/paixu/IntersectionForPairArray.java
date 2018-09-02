package com.paixu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Name: IntersectionForPairArray
 * Author: tunsh
 * Date: 2018-06-16 14:07
 */
public class IntersectionForPairArray {
    private List<Integer> inter = new ArrayList<Integer>(4);//存储压缩后的集合
    private Set<Integer> s = new HashSet<Integer>(4);//过滤压缩集合中的重复数字
    private Queue<Integer> arr1 = new ConcurrentLinkedDeque<Integer>();//存储原始数据的队列，链式存储
    private Queue<Integer> arr2 = new ConcurrentLinkedDeque<Integer>();
    private List<Integer> result = new ArrayList<Integer>();//结果集
    private List<Integer> intersec = new ArrayList<Integer>(2);//压缩集合的交集

    public List<Integer> intersection(int[]ar1,int[]ar2){
        if(ar1.length == 0 || ar2.length == 0 || ar1 == null || ar2 == null) return result;

        //1.把数据加载到队列中
        int len = Math.max(ar1.length, ar2.length);
        for (int i = 0;i < len;i++){
            if (i <= ar1.length-1){
                arr1.add(ar1[i]);
            }
            if (i <= ar2.length-1){
                arr2.add(ar2[i]);
            }
        }
        while(true) {
            //2.集合压缩
            inter.add(Collections.min(arr1));
            inter.add(Collections.max(arr1));
            inter.add(Collections.min(arr2));
            inter.add(Collections.max(arr2));

            for (int i = 0;i < inter.size();i++){//把压缩后的集合加入到set中
                s.add(inter.get(i));
            }
            int size = s.size();
            //下面开始寻找相同的数字

            if(size == 4){

            }
            //先求和
            int sum = computeSum(inter);
            int sum1 = computeSum(s);
            int res = sum - sum1;

            if (size == 3){
                if ((inter.get(0) == inter.get(1)) || (inter.get(2) == inter.get(3))){
                    return result;
                }
                else {
                    result.add(res);
                    arr1.remove(res);
                    arr2.remove(res);
                }
            }
            if (size == 2) {//有三个元素和两对儿元素重复的情况，收敛情况是两个压缩集合各自重复，三个元素重复的情况其结果是res/2
                if ((inter.get(0) == inter.get(1)) && (inter.get(2) == inter.get(3))) {
                    return result;
                }
                else {
                    if((inter.get(0) == inter.get(2)) && (inter.get(1) == inter.get(3))){
                        result.addAll(s);
                        for (int element:s){
                            arr1.remove(element);
                            arr2.remove(element);
                        }
                    } else {
                        result.add(res/2);
                        arr1.remove(res/2);
                        arr2.remove(res/2);
                    }

                }
            }
            if (size == 1) {
                result.addAll(s);
                return result;
            }
            //4.计算inter的交集,并分别去除两个集合中不在此范围内的元素
            intersec.add(Math.max(inter.get(0),inter.get(2)));
            intersec.add(Math.min(inter.get(1),inter.get(3)));

            if (intersec.get(0) > intersec.get(1)) break;//当size == 4并且两个压缩集合没有交集时，到此终止

            removeElement(arr1);
            removeElement(arr2);

            if (arr1.size() == 0 || arr2.size() == 0) break;

            s.clear();
            inter.clear();
            intersec.clear();
        }
        return result;

    }

    private void removeElement(Queue<Integer> queue) {
        Iterator<Integer> it = queue.iterator();
        while (it.hasNext()){
            int n = it.next();
            if (n < intersec.get(0) || n > intersec.get(1)) {
                queue.remove(n);
            }
        }
    }

    private int computeSum(Collection<Integer> col) {
        int sum = 0;
        for (int i :col){
            sum += i;
        }
        return sum;
    }

}
