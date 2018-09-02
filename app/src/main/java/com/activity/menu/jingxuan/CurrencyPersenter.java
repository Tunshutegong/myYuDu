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

public class CurrencyPersenter {
    private CurrencyView view;
    private Context context;
    public CurrencyPersenter(CurrencyView view, Context context){
        this.context = context;
        this.view = view;
    }

    /*
    * 获取书库信息
    * */
    public void getNewOrHotInfoTask(String block,String dis,String sort,int start){
        OkHttpClient okHttpClient=new OkHttpClient();
        GsonParser<CurrencyResult> parser = new GsonParser<>(CurrencyResult.class);
        String url = Api.zongHeTaoLunUrl(block,dis,sort,start);
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Myback<CurrencyResult>(parser) {
            @Override
            public void onResponse(CurrencyResult result) {
                view.showData(result);
            }
        });
    }
}
