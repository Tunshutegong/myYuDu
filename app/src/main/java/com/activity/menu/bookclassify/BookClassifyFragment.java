package com.activity.menu.bookclassify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tunsh.greendaodemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: BookClassifyFragment
 * Author: tunsh
 * Date: 2017-12-14 10:46
 */
public class BookClassifyFragment extends Fragment {
    private RecyclerView reclv;
    private BookCateResult result;
    private String pos;
    private List<BookCateResult.CateResult> list = new ArrayList<>();
    private BookClassfiyAdapter adapter;
    private GridView gv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_classify_fragment, container,
                false);
        gv = (GridView)view.findViewById(R.id.gv);
        adapter = new BookClassfiyAdapter(getActivity(),list);
        gv.setAdapter(adapter);
        Bundle args = getArguments();
        if(args!=null){
            result = (BookCateResult) args.getSerializable("result");
            pos = args.getString("pos");
            list.clear();
            if("0".equals(pos)){
                list.addAll(result.getMale());
                adapter.notifyDataSetChanged();
            }else if("1".equals(pos)){
                list.addAll(result.getFemale());
                adapter.notifyDataSetChanged();
            }else if("2".equals(pos)){
                list.addAll(result.getPress());
                adapter.notifyDataSetChanged();
            }
        }

        return view;
    }
}
