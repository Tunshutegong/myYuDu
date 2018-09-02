package com.activity.menu.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.PaiXu;
import com.example.tunsh.greendaodemo.R;
import com.paixu.FeiBo;
import com.paixu.IntersectionForPairArray;
import com.paixu.MySort;
import com.tree.Node;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Name: MineFragment
 * Author: tunsh
 * Date: 2017-12-04 11:24
 */
public class MineFragment extends Fragment {
    private TextView tv_qiandao;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment_layout, container, false);
        tv_qiandao = (TextView)view.findViewById(R.id.tv_qiandao);
        tv_qiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Node<String> a = new Node<String>("1");
//                Node<String> b = new Node<String>("2");
//                Node<String> c = new Node<String>("3");
//                a.left = b;
//                a.right = c;
//                midOrderWithoutRecurs(a);
//                int[] nums = {6,1,2,7,9};//需要排序的数组
//                PaiXu.sort(nums,0,nums.length-1);
//                Log.e("zym","--------------->"+ Arrays.toString(nums));
//                MySort.sort(nums,0,nums.length-1);
//                nums =  PaiXu.quickSort_not_recursion(nums);
//                Log.e("zym","--------------->"+ Arrays.toString(nums));
//                FeiBo.fb1();
//                Log.e("zym","---------->"+FeiBo.fb2(10));
//                FeiBo.fb2(10);
//                int[] arr = {1,3,5,7,9,11};
//               int key =  PaiXu.commonBinarySearch(arr,9);
//                int key = MySort.erFen(arr,9);
//               Log.e("zym","--------------->"+key);
//                int[] arr1 = {1,2,4,6};
//                int [] arr2 = {2,3,4,9};
//                PaiXu.jiaoJI(arr1,arr2);
                int[] arr1 = {1,8,5,7,9,11};
                int[] arr2 = {1,3,5,53,9,11};
                IntersectionForPairArray in = new IntersectionForPairArray();
                List<Integer> my = in.intersection(arr1,arr2);
                Log.e("zym","----------->"+my);


            }
        });
        LayoutInflater.from(getActivity());
        return view;
    }

    public void preOrder(Node<String> n) {

        if (n.left != null){
            preOrder(n.left);
        }
        if (n.right != null){
            preOrder(n.right);
        }
    }

    public void midOrderWithoutRecurs(Node<String> root) {
        if (root == null)
            return;

        Stack<Node<String>> s = new Stack<Node<String>>();
        Node<String> current;

        s.push(root);

        while (! s.isEmpty()) {
            current = s.peek();
            if (current.state == 0) {
                if (current.left != null)
                    s.push(current.left);
                current.state = 1;
            }
            else if (current.state == 1) {
                System.out.println(current.data);
                Log.e("zym","---------->"+current.data);
                current.state = 2;
            }
            else if (current.state == 2) {
                if (current.right != null)
                    s.push(current.right);
                current.state = 3;
            }
            else if (current.state == 3) {
                s.pop();
                current.state = 0;
            }
        }
    }
}
