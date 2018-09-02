package com.activity.menu.bookdetail;

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
import com.utils.DateHelper;

import java.util.List;


/**
 * description:
 * Created by ZYM on 2017/1/19.
 * copyright www.jiehun.com.cn
 */

public class CommentDetailAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<CommentDetailResult.Comments> comments;
    public CommentDetailAdapter(Context context, List<CommentDetailResult.Comments> comments){
        this.context = context;
        this.comments = comments;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        if(comments == null){
            return 0;
        }
        return comments.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.comment_detail_item_layout,null);
            holder.sdv = (SimpleDraweeView)convertView.findViewById(R.id.sdv);
            holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
            holder.tv_time = (TextView)convertView.findViewById(R.id.tv_time);
            holder.tv_zan = (TextView)convertView.findViewById(R.id.tv_zan);
            holder.tv_content = (TextView)convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        if(comments.get(position)!=null){
            if(comments.get(position)!=null){
                if(comments.get(position).getAuthor()!=null){
                    String imgUrl = Api.IMG_BASE_URL+comments.get(position).getAuthor().getAvatar();
                    Uri uri = Uri.parse(imgUrl);
                    holder.sdv.setImageURI(uri);
                    holder.tv_name.setText(comments.get(position).getAuthor().getNickname());
                }


            }
            holder.tv_zan.setText("点赞  "+comments.get(position).getLikeCount());
            holder.tv_content.setText(comments.get(position).getContent());
            holder.tv_time.setText(DateHelper.getInstance().getRencentTime(DateHelper.replaceTandZ(comments.get(position).getCreated())));
        }
        return convertView;
    }
    class ViewHolder{
        SimpleDraweeView sdv;
        TextView tv_name,tv_content,tv_time,tv_zan,tv_comment;
    }
}
