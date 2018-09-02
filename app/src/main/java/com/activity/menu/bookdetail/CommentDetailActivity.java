package com.activity.menu.bookdetail;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.activity.menu.BaseActivity;
import com.example.tunsh.greendaodemo.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.http.Myback;
import com.http.callpack.GsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.utils.Api;

import java.util.ArrayList;
import java.util.List;

public class CommentDetailActivity extends BaseActivity {
    private String name;
    private String title;
    private String cotent;
    private String time;
    private String url,zan;
    private String bookId;
    private View headView;
    private ListView lv;
    private List<CommentDetailResult.Comments> comments = new ArrayList<>();
    private CommentDetailAdapter adapter;
    private SimpleDraweeView sdv;
    private TextView tv_name,tv_content,tv_time,tv_zan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_detail);
        name = getIntent().getStringExtra("name");
        title = getIntent().getStringExtra("title");
        cotent = getIntent().getStringExtra("content");
        time = getIntent().getStringExtra("time");
        bookId = getIntent().getStringExtra("id");
        url = getIntent().getStringExtra("url");
        lv = (ListView)findViewById(R.id.lv);
        adapter = new CommentDetailAdapter(this,comments);
        headView = LayoutInflater.from(this).inflate(R.layout.comment_detail_head_view_layout,null);
        sdv = (SimpleDraweeView)headView.findViewById(R.id.sdv);
        tv_content = (TextView)headView.findViewById(R.id.tv_content);
        tv_name = (TextView)headView.findViewById(R.id.tv_name);
        tv_time = (TextView)headView.findViewById(R.id.tv_time);
        tv_zan = (TextView)headView.findViewById(R.id.tv_zan);
        lv.addHeaderView(headView);
        lv.setAdapter(adapter);
        getCommentTask();
    }

    public void getCommentTask(){
        OkHttpClient okHttpClient=new OkHttpClient();
        GsonParser<CommentDetailResult> parser = new GsonParser<>(CommentDetailResult.class);
        String commentUrl = Api.getCommentDetailListUrl(0,bookId);
        final Request request = new Request.Builder().url(commentUrl).build();
        okHttpClient.newCall(request).enqueue(new Myback<CommentDetailResult>(parser) {
            @Override
            public void onResponse(CommentDetailResult result) {
                if(url!=null){
                    sdv.setImageURI(Uri.parse(url));
                }
                tv_name.setText(name);
                tv_time.setText(time);
                tv_content.setText(cotent);
                tv_zan.setText(zan);
                if(result!=null&&result.getComments()!=null){
                    comments.clear();
                    comments.addAll(result.getComments());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
