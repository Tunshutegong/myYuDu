package com.activity.menu.bookdetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tunsh.greendaodemo.R;

import java.util.List;

/**
 * Name: ChapterListAdapter
 * Author: tunsh
 * Date: 2017-12-13 13:49
 */
public class ChapterListAdapter extends BaseAdapter {
    private List<ChapterListResult.MixTop.Chapters> list;
    private Context context;
    private LayoutInflater inflater;
    public ChapterListAdapter(Context context, List<ChapterListResult.MixTop.Chapters> list){
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);

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
            view = inflater.inflate(R.layout.chapter_item_layout,null);
            holder.tv = (TextView)view.findViewById(R.id.tv);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        if(list.get(i)!=null){
            holder.tv.setText(list.get(i).getTitle());
        }
        return view;
    }
    class ViewHolder{
        TextView tv;
    }
}
