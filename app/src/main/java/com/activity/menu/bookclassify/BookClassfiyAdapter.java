package com.activity.menu.bookclassify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.contentview.DensityUtil;
import com.example.tunsh.greendaodemo.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Name: BookClassfiyAdapter
 * Author: tunsh
 * Date: 2017-12-14 14:11
 */
public class BookClassfiyAdapter extends BaseAdapter {
    private LayoutInflater inflate;
    private List<BookCateResult.CateResult> list;
    private Context context;
    public BookClassfiyAdapter(Context context, List<BookCateResult.CateResult> list){
        this.context = context;
        inflate = LayoutInflater.from(context);
        this.list = list;
    }


    @Override
    public int getCount() {
        if(list == null){
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            holder = new ViewHolder();
            view = inflate.inflate(R.layout.book_classify_item_layout,null);
            holder.tvName = (TextView)view.findViewById(R.id.tvName);
            holder.tvBookCount = (TextView)view.findViewById(R.id.tvBookCount);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        if(list.get(i)!=null){
            holder.tvName.setText(list.get(i).getMajor());
            if(list.get(i).getMins()!=null){
                if(list.get(i).getMins().size()>2){
                    holder.tvBookCount.setText(list.get(i).getMins().get(0)+"/"+list.get(i).getMins().get(1));
                }
            }

        }

        return view;
    }
    class ViewHolder{
        TextView tvName,tvBookCount;
    }
}
