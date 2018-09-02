package com.activity.menu.bookdetail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.activity.menu.BaseActivity;
import com.activity.menu.bookstacks.BookRankResult;
import com.activity.menu.bookstacks.BookStackItemAdapter;
import com.activity.menu.bookstacks.BookStackItemListAdapter;
import com.example.tunsh.greendaodemo.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.http.Myback;
import com.http.callpack.GsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.utils.Api;

import java.util.ArrayList;
import java.util.List;

public class BookDetailsActivity extends BaseActivity implements View.OnClickListener {
    private String bookId = "";
    private SimpleDraweeView iv_cover;
    private TextView tv_name,tv_author,tv_tag,tvBiLi,tv_follower,tvlast,tvTitle;
    private TextView tv_intro,tv_retentionRatio,tv_serializeWordCount,tvAllComment;
    private boolean collapseLongIntro = true;
    private LinearLayout llChapter;
    private ViewFlipper vf;
    private String fengmianUrl = "";
    private String author = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        bookId = getIntent().getStringExtra("id");
        iv_cover = (SimpleDraweeView)findViewById(R.id.iv_cover);
        tv_name = (TextView)findViewById(R.id.tv_name);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tv_author = (TextView)findViewById(R.id.tv_author);
        tv_tag = (TextView)findViewById(R.id.tv_tag);
        tvBiLi = (TextView)findViewById(R.id.tvBiLi);
        tvAllComment = (TextView)findViewById(R.id.tvAllComment);
        tv_follower = (TextView)findViewById(R.id.tv_follower);
        tv_intro = (TextView)findViewById(R.id.tv_intro);
        tv_retentionRatio = (TextView)findViewById(R.id.tv_retentionRatio);
        tv_serializeWordCount = (TextView)findViewById(R.id.tv_serializeWordCount);
        tvlast = (TextView)findViewById(R.id.tvlast);
        vf = (ViewFlipper)findViewById(R.id.vf);
        llChapter = (LinearLayout)findViewById(R.id.llChapter);
        tv_intro.setOnClickListener(this);
        llChapter.setOnClickListener(this);
        tvAllComment.setOnClickListener(this);
        getNewOrHotInfoTask();
        getCommentTask();
    }

    public void getNewOrHotInfoTask(){
        OkHttpClient okHttpClient=new OkHttpClient();
        GsonParser<BookDetailResult> parser = new GsonParser<>(BookDetailResult.class);
        String url = Api.BOOKS+bookId;
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Myback<BookDetailResult>(parser) {
            @Override
            public void onResponse(BookDetailResult result) {
                if(result!=null){
                    if(result.getCover()!=null){
                        fengmianUrl = Api.IMG_BASE_URL+result.getCover();
                        iv_cover.setImageURI(Uri.parse(Api.IMG_BASE_URL+result.getCover()));
                    }
                    author = result.getAuthor();
                    tv_name.setText(result.getTitle());
                    tvTitle.setText(result.getTitle());
                    tv_author.setText(result.getAuthor());
                    int num = Integer.parseInt(result.getWordCount());
                    tv_tag.setText(result.getMinorCate()+" "+num/10000+"万字");
                    tvBiLi.setText(result.getRetentionRatio()+"%");
                    tv_intro.setText(result.getLongIntro());
                    tv_follower.setText(result.getLatelyFollower());
                    tv_retentionRatio.setText(result.getRetentionRatio()+"%");
                    tv_serializeWordCount.setText(result.getSerializeWordCount());
                    tvlast.setText(result.getLastChapter());

                }
            }
        });
    }
    public void getCommentTask(){
        OkHttpClient okHttpClient=new OkHttpClient();
        GsonParser<BookCommentResult> parser = new GsonParser<>(BookCommentResult.class);
        String commentUrl = Api.getCommentUrl(bookId);
        final Request request = new Request.Builder().url(commentUrl).build();
        okHttpClient.newCall(request).enqueue(new Myback<BookCommentResult>(parser) {
            @Override
            public void onResponse(BookCommentResult result) {
                if(result!=null&&result.getReviews()!=null){
                    startFlipping(result.getReviews());
                }
            }
        });
    }

    public void startFlipping(List<BookCommentResult.Reviews> reviews){
        vf.setInAnimation(BookDetailsActivity.this, R.anim.notice_in);
        vf.setOutAnimation(BookDetailsActivity.this, R.anim.notice_out);
        for (int i = 0; i < reviews.size(); i++) {
            View v = LayoutInflater.from(BookDetailsActivity.this).inflate(R.layout.notice_item_layout, null);
            TextView tvName = (TextView) v.findViewById(R.id.tv_name);
            SimpleDraweeView sdv = (SimpleDraweeView)v.findViewById(R.id.sdv);
            TextView tv_content = (TextView) v.findViewById(R.id.tv_content);
            if(reviews.get(i).getAuthor()!=null){
                String imgUrl = Api.IMG_BASE_URL+reviews.get(i).getAuthor().getAvatar();
                Uri uri = Uri.parse(imgUrl);
                sdv.setImageURI(uri);
                tvName.setText(reviews.get(i).getAuthor().getNickname());
            }
            tv_content.setText(reviews.get(i).getContent());

            vf.addView(v);
        }
        vf.startFlipping();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_intro:
                if (collapseLongIntro) {
                    tv_intro.setMaxLines(20);
                    collapseLongIntro = false;
                } else {
                    tv_intro.setMaxLines(4);
                    collapseLongIntro = true;
                }
                break;
            case R.id.llChapter:
                Intent intent = new Intent(BookDetailsActivity.this,ChapterListActivity.class);
                intent.putExtra("id",bookId);
                startActivity(intent);
                break;
            case R.id.tvAllComment:
                Intent intent1 = new Intent(BookDetailsActivity.this,CommentListActivity.class);
                intent1.putExtra("id",bookId);
                intent1.putExtra("author",author);
                intent1.putExtra("url",fengmianUrl);
                startActivity(intent1);
                break;
            default:
        }
    }
}
