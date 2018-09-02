package com.activity.menu.bookclassify;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.activity.menu.BaseActivity;
import com.activity.menu.jingxuan.HottestFragment;
import com.example.tunsh.greendaodemo.NewsFragmentPagerAdapter;
import com.example.tunsh.greendaodemo.R;
import com.http.Myback;
import com.http.callpack.GsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.tablayout.SlidingTabLayout;
import com.utils.Api;

import java.util.ArrayList;
/*
* 书籍分类
* */

public class BookClassifyActivity extends BaseActivity {
    private SlidingTabLayout stl;
    private ViewPager vp;
    private NewsFragmentPagerAdapter mAdapetr;
    private String [] titles = {"男生","女生","出版","漫画"};
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();// 所要加载的Fragments

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_classify);
        stl = (SlidingTabLayout)findViewById(R.id.stl);
        vp = (ViewPager)findViewById(R.id.vp);
        stl.setOverScrollMode(View.FOCUSABLES_TOUCH_MODE);
        mAdapetr = new NewsFragmentPagerAdapter(getSupportFragmentManager());
        getStacksInfoTask();

    }

    public void getStacksInfoTask(){
        OkHttpClient okHttpClient=new OkHttpClient();
        GsonParser<BookCateResult> parser = new GsonParser<>(BookCateResult.class);
        Request request = new Request.Builder().url(Api.BOOK_CLASS).build();
        okHttpClient.newCall(request).enqueue(new Myback<BookCateResult>(parser) {
            @Override
            public void onResponse(BookCateResult result) {
                mFragmentList.clear();
                for(int i=0;i<titles.length;i++){
                    BookClassifyFragment myFragment = new BookClassifyFragment();
                    Bundle args = new Bundle();
                    args.putSerializable("result", result);
                    args.putString("pos",i+"");
                    myFragment.setArguments(args);
                    mFragmentList.add(myFragment);

                }
                mAdapetr.setFragments(mFragmentList);
                vp.setAdapter(mAdapetr);
                stl.setViewPager(vp, titles);
                stl.notifyDataSetChanged();
            }
        });
    }
}
