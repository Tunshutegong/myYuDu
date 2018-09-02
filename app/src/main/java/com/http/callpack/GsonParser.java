package com.http.callpack;

import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class GsonParser<T> implements Parser<T> {
    private Class<T> mClass=null;
    public GsonParser(Class<T> clazz){
        if (clazz==null){
            throw new IllegalArgumentException("Class can't be null");
        }
        this.mClass=clazz;
    }
    @Override
    public T parse(Response response) {
        try {
            Gson gson=new Gson();
            String str=response.body().string();
            Log.e("zym","-->"+str);
            T t=gson.fromJson(str,mClass);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
