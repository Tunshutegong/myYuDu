package com.example.tunsh.greendaodemo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.activity.menu.BaseActivity;
import com.activity.menu.bookstacks.BookStacksChildFragment;
import com.tablayout.SlidingTabLayout;

import java.util.ArrayList;

public class ReadBookActivity extends BaseActivity {
    private SlidingTabLayout stl;
    private ViewPager vp;
    private NewsFragmentPagerAdapter mAdapetr;
    private String [] titles = {"测试","测试","测试","测试","测试","测试","测试"};
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();// 所要加载的Fragments
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);
        stl = (SlidingTabLayout) findViewById(R.id.stl);
        vp = (ViewPager)findViewById(R.id.vp);
        stl.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mAdapetr = new NewsFragmentPagerAdapter(getSupportFragmentManager());
        mFragmentList.clear();
        for(int i=0;i<titles.length;i++){
            BookStacksChildFragment myFragment = new BookStacksChildFragment();
            mFragmentList.add(myFragment);

        }
        mAdapetr.setFragments(mFragmentList);
        vp.setAdapter(mAdapetr);
        stl.setViewPager(vp, titles);
        stl.notifyDataSetChanged();
    }
}
