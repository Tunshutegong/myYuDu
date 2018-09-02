package com.read;

import android.content.Context;
import android.util.Log;

import com.afa.tourism.greendao.gen.BookDao;
import com.example.tunsh.greendaodemo.AFaApplication;
import com.example.tunsh.greendaodemo.Book;
import com.example.tunsh.greendaodemo.Chapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: ReadPersenter
 * Author: tunsh
 * Date: 2017-12-01 16:45
 */
public class ReadPersenter {
    private Context context;
    private ReadView view;
    private BookDao dao;
    private Book book;
    private ReadAdapter readAdapter;
    private List<Chapter> chapters = new ArrayList<>();
    public ReadPersenter(Context context,ReadView view){
        this.context = context;
        this.view = view;
        dao = AFaApplication.getApplication().getDaoSession().getBookDao();
    }
    public void getBook(int currentChapter,String  bookName){
        List<Book> mylist = dao.loadAll();
        if(mylist != null && mylist.size() > 0){
            for(int i=0;i<mylist.size();i++){
                if(bookName.equals(mylist.get(i).getBookName())){
                    book = mylist.get(i);
                    break;
                }
            }


        }
        if(book!=null&&book.getChapters()!=null){
            view.getchapterCount(book.getChapters().size());
            view.getChapters(book.getChapters());
            chapters.clear();
            chapters.addAll(book.getChapters());
            readAdapter = new ReadAdapter();
            for(int i=0;i<book.getChapters().size();i++){
                readAdapter.addData(i,chapters.get(i));
            }
            view.showContent(readAdapter, currentChapter);
        }
    }

    public void getChapterContent(int currentChapter){
        if(book!=null){
            readAdapter.addData(currentChapter,chapters.get(currentChapter));
            view.showContent(readAdapter, currentChapter);
        }
    }

    public void preOrnextChapter(int currentChapter){
        readAdapter.clear();
        getChapterContent(currentChapter);

    }
}
