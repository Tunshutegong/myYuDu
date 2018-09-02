package com.activity.menu.bookshelf.adapter;

import android.support.annotation.IdRes;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;


public class CommonViewHolder extends BaseViewHolder {
    public CommonViewHolder(View view) {
        super(view);
    }

    public CommonViewHolder setSelected(@IdRes int viewId, boolean selected) {
        getView(viewId).setSelected(selected);
        return this;
    }



}
