package com.activity.menu.jingxuan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tunsh.greendaodemo.R;


/**
 * description:最热评论
 * Created by ZYM on 2017/1/12.
 * copyright www.jiehun.com.cn
 */

public class HottestFragment extends Fragment implements FindCommentView {
    private FindCommentPersenter persenter;
    private RecyclerView reclv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newest_release_layout, container,
                false);
        reclv = (RecyclerView)view.findViewById(R.id.rclv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        reclv.setLayoutManager(layoutManager);
        persenter = new FindCommentPersenter(this,getActivity());
        persenter.getNewOrHotInfoTask("updated",0);
        return view;
    }

    @Override
    public void showData(BookShortResult result) {
        if(result!=null&&result.getHelps()!=null){
            reclv.setAdapter(new BookShortAdapter(getActivity(),result.getHelps()));
        }
    }
}
