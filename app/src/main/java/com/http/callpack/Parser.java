package com.http.callpack;


import com.squareup.okhttp.Response;

public interface Parser<T> {
    T parse(Response response);
}
