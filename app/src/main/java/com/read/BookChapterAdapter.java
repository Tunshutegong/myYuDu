package com.read;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.activity.menu.bookshelf.adapter.CommonAdapter;
import com.activity.menu.bookshelf.adapter.CommonViewHolder;
import com.example.tunsh.greendaodemo.Chapter;
import com.example.tunsh.greendaodemo.R;

import java.util.List;


public class BookChapterAdapter extends CommonAdapter<Chapter> {


    private int textColor = -1;
    private int currentChapter = 0;

    public int getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(int currentChapter) {
        this.currentChapter = currentChapter;
    }

    public BookChapterAdapter(List<Chapter> data) {
        super(R.layout.list_chapter_item, data);
    }

    @Override
    public int getParentPosition(@NonNull Chapter item) {
        return super.getParentPosition(item);
    }



    @Override
    protected void convert(CommonViewHolder helper, Chapter item) {
        TextView textView=helper.getView(R.id.tv_section_name);
        textView.setText(item.getChapterName());


        if(textColor==-1){
            textView.setTextColor(ContextCompat.getColor(mContext,R.color.textPrimary));
        }else{
            textView.setTextColor(textColor);
        }
        if(currentChapter == helper.getAdapterPosition()){
            textView.setTextColor(ContextCompat.getColor(mContext,R.color.colorTheme));
        }else {
            textView.setTextColor(ContextCompat.getColor(mContext,R.color.textPrimary));
        }
    }

    public void setTextColor(int color) {
        textColor=color;
        notifyDataSetChanged();
    }

}
