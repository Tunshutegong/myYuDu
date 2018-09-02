package com.activity.menu.jingxuan;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.activity.menu.BaseActivity;
import com.example.tunsh.greendaodemo.NewsFragmentPagerAdapter;
import com.example.tunsh.greendaodemo.R;
import com.tablayout.SlidingTabLayout;

import java.util.ArrayList;

public class BookShortActivity extends BaseActivity {
    private SlidingTabLayout stl;
    private ViewPager vp;
    private NewsFragmentPagerAdapter mAdapetr;
    private String [] titles = {"全部","精品"};
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();// 所要加载的Fragments

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_short);
        stl = (SlidingTabLayout)findViewById(R.id.stl);
        vp = (ViewPager)findViewById(R.id.vp);
        stl.setOverScrollMode(View.FOCUSABLES_TOUCH_MODE);
        mAdapetr = new NewsFragmentPagerAdapter(getSupportFragmentManager());
        mFragmentList.clear();
        for(int i=0;i<titles.length;i++){
            HottestFragment myFragment = new HottestFragment();
            mFragmentList.add(myFragment);

        }
        mAdapetr.setFragments(mFragmentList);
        vp.setAdapter(mAdapetr);
        stl.setViewPager(vp, titles);
        stl.notifyDataSetChanged();
    }
}
