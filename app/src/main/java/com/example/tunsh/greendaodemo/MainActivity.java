package com.example.tunsh.greendaodemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.activity.menu.MenuActivity;
import com.activity.menu.importbooks.MyAdapter;
import com.afa.tourism.greendao.gen.BookDao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends Activity {
    private BookDao dao;
    private ListView lv;
    private List<File> files = new ArrayList<>();
    private MyAdapter adapter;
    private TextView tvRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvRead = (TextView)findViewById(R.id.tvRead);
        lv = (ListView)findViewById(R.id.lv);
        dao = AFaApplication.getApplication().getDaoSession().getBookDao();
//        Book book = new Book(null, "回到明朝", "月关");
//        Book book1 = new Book(null,"明朝那些事","当年明月");
//        dao.insert(book);
//        dao.insert(book1);
//        addCharter();
//        adapter = new MyAdapter(this,files);
//        lv.setAdapter(adapter);
        files.clear();
//        searchLocationBook();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                List<File> myBooks = new ArrayList<File>();
                myBooks.add(files.get(i));
                Book myBook = new Book();
                myBook.setBookName(files.get(i).getName());
                dao.insert(myBook);
                importBooks(myBooks,myBook.getId());
            }
        });

        tvRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> mylist = dao.loadAll();
                if(mylist!=null&&mylist.size()>0){
                    for(int i=0;i<mylist.size();i++){
                        Log.e("zy","---------------->"+mylist.get(i).getBookName());
                    }
                }

                startActivity(new Intent(MainActivity.this,MenuActivity.class));
//                startActivity(new Intent(MainActivity.this,ReadActivity.class));
            }
        });


    }

    public void searchLocationBook(){
        Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(ObservableEmitter<File> e) throws Exception {
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)){
                    searchBook(e,new File(Environment.getExternalStorageDirectory().getAbsolutePath()));
                }
                e.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SimpleObserver<File>() {
                    @Override
                    public void onNext(File value) {
                        files.add(value);
                        adapter.notifyDataSetChanged();
//                        mView.addNewBook(value);
                    }

                    @Override
                    public void onComplete() {
//                        mView.searchFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    private void searchBook(ObservableEmitter<File> e, File parentFile) {
        if (null != parentFile && parentFile.listFiles().length > 0) {
            File[] childFiles = parentFile.listFiles();
            for (int i = 0; i < childFiles.length; i++) {
                if (childFiles[i].isFile() && childFiles[i].getName().substring(childFiles[i].getName().lastIndexOf(".") + 1).equalsIgnoreCase("txt") && childFiles[i].length() > 300*1024&&!childFiles[i].getName().contains("log")&&!childFiles[i].getName().contains("app")) {   //300kb
                    e.onNext(childFiles[i]);
                    continue;
                }
                if (childFiles[i].isDirectory() && childFiles[i].listFiles().length > 0) {
                    searchBook(e, childFiles[i]);
                }
            }
        }
    }

    private void addCharter() {
        List<Book> list = dao.loadAll();
        for(int i=0;i<3;i++){
            Chapter chapter = new Chapter();
            chapter.setChapterName("回到明朝第一章"+i);
            chapter.setChapterContent("回到明朝第一章的内容"+i);
            if(list!=null&& list.size()>0){
                chapter.setChapterId(list.get(0).getId());
                AFaApplication.getApplication().getDaoSession().getChapterDao().insert(chapter);
            }
        }
        for(int i=0;i<3;i++){
            Chapter chapter = new Chapter();
            chapter.setChapterName("明朝那些事第一章"+i);
            chapter.setChapterContent("明朝那些事第一章的内容"+i);
            if(list!=null&& list.size()>0){
                chapter.setChapterId(list.get(1).getId());
                AFaApplication.getApplication().getDaoSession().getChapterDao().insert(chapter);
            }
        }


        List<Book> mylist = dao.loadAll();
        if(mylist!=null&&mylist.size()>0){
            if(mylist.get(0).getChapters()!=null){
                for(int k=0;k<mylist.get(0).getChapters().size();k++){
                    Log.e("love","--------->"+mylist.get(0).getChapters().get(k).getChapterContent());
                }
            }
        }
        if(mylist!=null&&mylist.size()>0){
            if(mylist.get(1).getChapters()!=null){
                for(int k=0;k<mylist.get(1).getChapters().size();k++){
                    Log.e("love","--------->"+mylist.get(1).getChapters().get(k).getChapterContent());
                }
            }
        }
    }

    public void importBooks(List<File> books, final Long id){
        Observable.fromIterable(books).flatMap(new Function<File, ObservableSource<LocBook>>() {
            @Override
            public ObservableSource<LocBook> apply(File file) throws Exception {
                return AnalysisBook.getInstance().analysisBook(file,id);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<LocBook>() {
                    @Override
                    public void onNext(LocBook value) {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
//                        mView.addSuccess();
                        Log.e("zy","------------->"+"success");
                    }
                });
    }
}
