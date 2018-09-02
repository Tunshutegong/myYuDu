package com.activity.menu.bookstacks;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ScrollingView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.activity.menu.bookclassify.BookClassifyActivity;
import com.activity.menu.jingxuan.BookShortResult;
import com.activity.menu.view.TSPScrollView;
import com.activity.menu.view.UnscrollableGridView;
import com.activity.menu.view.UnscrollableListView;
import com.example.tunsh.greendaodemo.R;
import com.http.Myback;
import com.http.callpack.GsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.utils.Api;
import com.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: BookStacksChildFragment
 * Author: tunsh
 * Date: 2017-11-30 11:52
 */
public class BookStacksChildFragment extends Fragment implements View.OnClickListener {
    private UnscrollableGridView ungv1,ungv2;
    private UnscrollableListView unlv;
    private String myUrl = "";
    private String tag;
    private ImageView img;
    private TSPScrollView sv;
    private List<BookRankResult.Books> list1 = new ArrayList<>();//8个
    private List<BookRankResult.Books> list2 = new ArrayList<>();//4个
    private List<BookRankResult.Books> list3 = new ArrayList<>();//3个
    private TitleChangeListener changeListener;
    private LinearLayout llBookClassify;

    public TitleChangeListener getChangeListener() {
        return changeListener;
    }

    public void setChangeListener(TitleChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment_layout,null);
        ungv1 = (UnscrollableGridView)view.findViewById(R.id.ungv1);
        ungv2 = (UnscrollableGridView)view.findViewById(R.id.ungv2);
        unlv = (UnscrollableListView)view.findViewById(R.id.unlv);
        sv = (TSPScrollView)view.findViewById(R.id.sv);
        img = (ImageView)view.findViewById(R.id.img);
        llBookClassify = (LinearLayout)view.findViewById(R.id.llBookClassify);
        llBookClassify.setOnClickListener(this);
        Bundle bundle = getArguments();
        if(bundle!=null){
            tag = bundle.getString("tag");
        }
        if("0".equals(tag)){
            myUrl = "564d8003aca44f4f61850fcd";
            img.setBackgroundResource(R.drawable.banner2);
        }else if("1".equals(tag)){
            img.setBackgroundResource(R.drawable.banner3);
            myUrl = "564d81151109835664770ad7";
        }else if("2".equals(tag)){
            img.setBackgroundResource(R.drawable.banner4);
            myUrl = "564d80457408cfcd63ae2dd0";
        }
        getNewOrHotInfoTask();
        sv.setOnScrollListener(new TSPScrollView.OnScrollListener() {
            @Override
            public void onScrollchanged(int t) {
                float scale = (float) t / DensityUtil.dp2px(getActivity(),120);
                float alpha = (255 * scale);
                if(t<=0){
                    if(changeListener!=null){
                        changeListener.change(0);
                    }
                }else if(t<DensityUtil.dp2px(getActivity(),120)&&t>0){
                    if(changeListener!=null){
                        changeListener.change((int)alpha);
                    }
                }else {
                    if(changeListener!=null){
                        changeListener.change(255);
                    }
                }

            }

            @Override
            public void onTouchUp() {

            }

            @Override
            public void onTouchDown() {

            }
        });
        return view;
    }

    public void getNewOrHotInfoTask(){
        OkHttpClient okHttpClient=new OkHttpClient();
        GsonParser<BookRankResult> parser = new GsonParser<>(BookRankResult.class);
        String url = Api.JINGXUANBASE+myUrl;
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Myback<BookRankResult>(parser) {
            @Override
            public void onResponse(BookRankResult result) {
                if(result!=null&&result.getRanking()!=null){

                    if(result.getRanking().getBooks().size()>8){
                        list1.clear();
                        list1.addAll(result.getRanking().getBooks().subList(0,8));
                        ungv1.setAdapter(new BookStackItemAdapter(getActivity(),list1));
                    }
                    if(result.getRanking().getBooks().size()>12){
                        list2.clear();
                        list2.addAll(result.getRanking().getBooks().subList(8,12));
                        ungv2.setAdapter(new BookStackItemAdapter(getActivity(),list2));
                    }
                    if(result.getRanking().getBooks().size()>16){
                        list3.clear();
                        list3.addAll(result.getRanking().getBooks().subList(12,16));
                        unlv.setAdapter(new BookStackItemListAdapter(getActivity(),list3));
                    }
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.llBookClassify:
                Intent intent = new Intent(getActivity(), BookClassifyActivity.class);
                startActivity(intent);
                break;
                default:
        }
    }

    public interface TitleChangeListener{
        void change(int a);
    }
}
