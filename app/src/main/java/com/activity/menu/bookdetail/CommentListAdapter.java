package com.activity.menu.bookdetail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tunsh.greendaodemo.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.utils.Api;
import com.utils.DateHelper;

import java.util.List;


/**
 * description:
 * Created by ZYM on 2017/1/19.
 * copyright www.jiehun.com.cn
 */

public class CommentListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<BookCommentResult.Reviews> list;
    public CommentListAdapter(Context context, List<BookCommentResult.Reviews> list){
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
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.comment_item_layout,null);
            holder.sdv = (SimpleDraweeView)convertView.findViewById(R.id.sdv);
            holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
            holder.tv_time = (TextView)convertView.findViewById(R.id.tv_time);
            holder.tv_zan = (TextView)convertView.findViewById(R.id.tv_zan);
            holder.tv_content = (TextView)convertView.findViewById(R.id.tv_content);
            holder.ll = (LinearLayout)convertView.findViewById(R.id.ll);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        if(list.get(position)!=null){
            if(list.get(position).getAuthor()!=null){
                String imgUrl = Api.IMG_BASE_URL+list.get(position).getAuthor().getAvatar();
                Uri uri = Uri.parse(imgUrl);
                holder.sdv.setImageURI(uri);
                holder.tv_name.setText(list.get(position).getAuthor().getNickname());
            }
            holder.tv_zan.setText("点赞  "+list.get(position).getLikeCount());
            holder.tv_content.setText(list.get(position).getContent());
            holder.tv_time.setText(DateHelper.getInstance().getRencentTime(DateHelper.replaceTandZ(list.get(position).getUpdated())));
            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,CommentDetailActivity.class);
                    if(list.get(position).getAuthor()!=null){
                        intent.putExtra("name",list.get(position).getAuthor().getNickname());
                    }
                    intent.putExtra("time",DateHelper.getInstance().getRencentTime(DateHelper.replaceTandZ(list.get(position).getUpdated())));
                    intent.putExtra("zan",list.get(position).getLikeCount());
                    intent.putExtra("title",list.get(position).getTitle());
                    intent.putExtra("id",list.get(position).get_id());
                    intent.putExtra("content",list.get(position).getContent());
                    intent.putExtra("url",Api.IMG_BASE_URL+list.get(position).getAuthor().getAvatar());
                    context.startActivity(intent);

                }
            });
        }
        return convertView;
    }
    class ViewHolder{
        SimpleDraweeView sdv;
        TextView tv_name,tv_content,tv_time,tv_zan,tv_comment;
        LinearLayout ll;
    }
}
