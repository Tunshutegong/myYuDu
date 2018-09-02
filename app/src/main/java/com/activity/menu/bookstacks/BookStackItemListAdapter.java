package com.activity.menu.bookstacks;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tunsh.greendaodemo.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.utils.Api;

import java.util.List;

/**
 * Name: BookStackItemAdapter
 * Author: tunsh
 * Date: 2017-12-12 14:10
 */
public class BookStackItemListAdapter extends BaseAdapter {
    private Context context;
    private List<BookRankResult.Books> list;
    private LayoutInflater inflater;
    public BookStackItemListAdapter(Context context, List<BookRankResult.Books> list){
        this.context = context;
        this.list = list;
        if(context == null){
            return;
        }
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
        ViewHolder holder ;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.book_stack_list_item_layout,null);
            holder.sdv = (SimpleDraweeView)view.findViewById(R.id.sdv);
            holder.tvName = (TextView)view.findViewById(R.id.tvName);
            holder.tvAuthor =  (TextView)view.findViewById(R.id.tvAuthor);
            holder.tv_state =  (TextView)view.findViewById(R.id.tv_state);
            holder.tvContent =  (TextView)view.findViewById(R.id.tvContent);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        if(list.get(i)!=null){
            holder.tvAuthor.setText(list.get(i).getAuthor());
            holder.tvName.setText(list.get(i).getTitle());
            holder.tv_state.setText(list.get(i).getMajorCate());
            holder.tvContent.setText(list.get(i).getShortIntro());
            if(list.get(i).getCover()!=null){
                holder.sdv.setImageURI(Uri.parse(Api.IMG_BASE_URL+list.get(i).getCover()));
            }

        }
        return view;
    }

    class ViewHolder{
        TextView tvAuthor,tvName,tv_state,tvContent;
        SimpleDraweeView sdv;
    }
}
