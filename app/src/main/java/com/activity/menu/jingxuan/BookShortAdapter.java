package com.activity.menu.jingxuan;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tunsh.greendaodemo.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.utils.Api;
import com.utils.DateHelper;

import java.util.List;


/**
 * description:
 * Created by ZYM on 2017/1/13.
 * copyright www.jiehun.com.cn
 */

public class BookShortAdapter extends  RecyclerView.Adapter<BookShortAdapter.MyHolder> {
    private Context context;
    private List<BookShortResult.Helps> list;
    private LayoutInflater inflater;

    public BookShortAdapter(Context context, List<BookShortResult.Helps> list){
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.book_short_item_layout,parent,false);
        BookShortAdapter.MyHolder myHolder = new BookShortAdapter.MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        if(list.get(position)!=null){
            if(list.get(position).getAuthor()!=null){
                //显示头像
                String imgUrl = list.get(position).getAuthor().getAvatar();
                imgUrl = Api.IMG_BASE_URL+imgUrl;
                Uri uri = Uri.parse(imgUrl);
                holder.sdv.setImageURI(uri);
                //显示名字
                holder.tv_name.setText(list.get(position).getAuthor().getNickname());
            }
            holder.tv_title.setText(list.get(position).getTitle());
            //时间
            if(list.get(position).getUpdated()!=null){
                holder.tv_time.setText(DateHelper.getInstance().getRencentTime(DateHelper.replaceTandZ(list.get(position).getUpdated())));
            }
            holder.tv_zan.setText("点赞 "+list.get(position).getLikeCount());
            holder.tv_comment.setText("评论 "+list.get(position).getCommentCount());

        }

    }

    @Override
    public int getItemCount() {
        if(list == null){
            return 0;
        }
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView sdv;
        TextView tv_name,tv_title,tv_time,tv_zan,tv_comment;

        public MyHolder(View itemView) {
            super(itemView);
            sdv = (SimpleDraweeView)itemView.findViewById(R.id.sdv);
            tv_name = (TextView)itemView.findViewById(R.id.tv_name);
            tv_title = (TextView)itemView.findViewById(R.id.tv_title);
            tv_time = (TextView)itemView.findViewById(R.id.tv_time);
            tv_zan = (TextView)itemView.findViewById(R.id.tv_zan);
            tv_comment = (TextView)itemView.findViewById(R.id.tv_comment);
        }
    }
}
