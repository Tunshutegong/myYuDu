package com.activity.menu.jingxuan;

import android.content.Context;

import com.http.Myback;
import com.http.callpack.GsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.utils.Api;


/**
 * description:
 * Created by ZYM on 2017/1/13.
 * copyright www.jiehun.com.cn
 */

public class FindCommentPersenter {
    private FindCommentView view;
    private Context context;
    public FindCommentPersenter(FindCommentView view, Context context){
        this.context = context;
        this.view = view;
    }

    /*
    * 获取书库信息
    * */
    public void getNewOrHotInfoTask(String sort, int start){
        OkHttpClient okHttpClient=new OkHttpClient();
        GsonParser<BookShortResult> parser = new GsonParser<>(BookShortResult.class);
        String url = Api.getApiUrl(sort,start);
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Myback<BookShortResult>(parser) {
            @Override
            public void onResponse(BookShortResult result) {
                view.showData(result);
            }
        });
    }
}
