package com.activity.menu.bookdetail;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.activity.menu.BaseActivity;
import com.example.tunsh.greendaodemo.R;
import com.http.Myback;
import com.http.callpack.GsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.utils.Api;

import java.util.ArrayList;
import java.util.List;

public class ChapterListActivity extends BaseActivity {
    private ListView lv;
    private String bookId;
    private List<ChapterListResult.MixTop.Chapters> list = new ArrayList<>();
    private ChapterListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);
        bookId = getIntent().getStringExtra("id");
        lv = (ListView)findViewById(R.id.lv);
        list.clear();
        adapter = new ChapterListAdapter(this,list);
        lv.setAdapter(adapter);
        getNewOrHotInfoTask();
    }
    public void getNewOrHotInfoTask(){
        OkHttpClient okHttpClient=new OkHttpClient();
        GsonParser<ChapterListResult> parser = new GsonParser<>(ChapterListResult.class);
        String url = Api.getChapterUrl(bookId);
        final Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Myback<ChapterListResult>(parser) {
            @Override
            public void onResponse(ChapterListResult result) {
                if(result!=null&&result.getMixToc()!=null){
                    list.clear();
                    if(result.getMixToc().getChapters()!=null){
                        list.addAll(result.getMixToc().getChapters());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
