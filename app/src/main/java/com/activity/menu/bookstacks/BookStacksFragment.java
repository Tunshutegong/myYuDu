package com.activity.menu.bookstacks;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tunsh.greendaodemo.NewsFragmentPagerAdapter;
import com.example.tunsh.greendaodemo.R;
import com.tablayout.SlidingTabLayout;

import java.util.ArrayList;

/**
 * Name: BookStacksFragment
 * Author: tunsh
 * Date: 2017-12-04 11:23
 */
public class BookStacksFragment extends Fragment implements BookStacksChildFragment.TitleChangeListener{
    private SlidingTabLayout stl;
    private ViewPager vp;
    private NewsFragmentPagerAdapter mAdapetr;
    private String [] titles = {"精选","女生","男生","二次元"};
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();// 所要加载的Fragments
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_stacks_fragment_layout, container, false);
        stl = (SlidingTabLayout) view.findViewById(R.id.stl);
        vp = (ViewPager)view.findViewById(R.id.vp);
        stl.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mAdapetr = new NewsFragmentPagerAdapter(getChildFragmentManager());
        mFragmentList.clear();
        for(int i=0;i<titles.length;i++){
            BookStacksChildFragment myFragment = new BookStacksChildFragment();
            Bundle bundle = new Bundle();
            bundle.putString("tag",i+"");
            myFragment.setArguments(bundle);
            myFragment.setChangeListener(this);
            mFragmentList.add(myFragment);

        }
        mAdapetr.setFragments(mFragmentList);
        vp.setAdapter(mAdapetr);
        stl.setViewPager(vp, titles);
        stl.notifyDataSetChanged();
        return view;
    }

    @Override
    public void change(int alpha) {
        stl.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
    }
}
