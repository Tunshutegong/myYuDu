package com.http;


import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


/**
 * description:
 * Created by ZYM on 2017/1/9.
 * copyright www.jiehun.com.cn
 */

public class MyOkHttp extends OkHttpClient {
    private OkHttpClient okHttpClient;
    public MyOkHttp(OkHttpClient client){
        OkHttpClient clone = client.clone();
        clone.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                return response;
            }
        });
        okHttpClient=clone;
    }
    public OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }

}
