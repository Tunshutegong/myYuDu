package com.activity.menu.bookdetail;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class CommentListActivity extends BaseActivity {
    private ListView lv;
    private String bookId,author,fengmian;
    private CommentListAdapter adapter;
    private List<BookCommentResult.Reviews> reviews = new ArrayList<>();
    private View headView;
    private SimpleDraweeView sdv;
    private TextView tvAuthor,tvPingLun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        headView = LayoutInflater.from(this).inflate(R.layout.comment_head_view_layout,null);
        bookId = getIntent().getStringExtra("id");
        author = getIntent().getStringExtra("author");
        fengmian = getIntent().getStringExtra("url");
        sdv = (SimpleDraweeView)headView.findViewById(R.id.sdv);
        tvAuthor = (TextView)headView.findViewById(R.id.tvAuthor);
        tvPingLun = (TextView)headView.findViewById(R.id.tvPingLun);
        lv = (ListView)findViewById(R.id.lv);
        adapter = new CommentListAdapter(this,reviews);
        lv.addHeaderView(headView);
        lv.setAdapter(adapter);
        getCommentTask();
    }
    public void getCommentTask(){
        OkHttpClient okHttpClient=new OkHttpClient();
        GsonParser<BookCommentResult> parser = new GsonParser<>(BookCommentResult.class);
        String commentUrl = Api.getCommentListUrl(0,bookId);
        final Request request = new Request.Builder().url(commentUrl).build();
        okHttpClient.newCall(request).enqueue(new Myback<BookCommentResult>(parser) {
            @Override
            public void onResponse(BookCommentResult result) {
                if(result!=null&&result.getReviews()!=null){
                    tvPingLun.setText(result.getTotal()+"人评论  37人评分");
                    tvAuthor.setText(author);
                    if(fengmian!=null){
                        sdv.setImageURI(Uri.parse(fengmian));
                    }
                    reviews.clear();
                    reviews.addAll(result.getReviews());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

}
