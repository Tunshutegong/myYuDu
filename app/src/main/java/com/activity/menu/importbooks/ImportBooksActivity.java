package com.activity.menu.importbooks;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.activity.menu.BaseActivity;
import com.activity.menu.view.CustomDialog;
import com.afa.tourism.greendao.gen.BookDao;
import com.example.tunsh.greendaodemo.AFaApplication;
import com.example.tunsh.greendaodemo.AnalysisBook;
import com.example.tunsh.greendaodemo.Book;
import com.example.tunsh.greendaodemo.LocBook;
import com.example.tunsh.greendaodemo.R;
import com.example.tunsh.greendaodemo.SimpleObserver;

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

public class ImportBooksActivity extends BaseActivity {
    private ListView lv;
    private List<File> files = new ArrayList<>();
    private List<Boolean> booleanList = new ArrayList<>();
    private MyAdapter adapter;
    private TextView tvCount,tvAddNum,tvAllChose;
    private LinearLayout llBar,llCount;
    private List<File> checkList = new ArrayList<>();
    private BookDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_books);
        dao = AFaApplication.getApplication().getDaoSession().getBookDao();
        llBar = (LinearLayout)findViewById(R.id.llBar);
        llCount = (LinearLayout)findViewById(R.id.llCount);
        lv = (ListView)findViewById(R.id.lv);
        tvCount = (TextView)findViewById(R.id.tvCount);
        tvAddNum = (TextView)findViewById(R.id.tvAddNum);
        tvAllChose = (TextView)findViewById(R.id.tvAllChose);
        adapter = new MyAdapter(this,files,booleanList);
        lv.setAdapter(adapter);
        files.clear();
        booleanList.clear();
        setListener();
        searchLocationBook();
    }

    public void setListener(){
        adapter.setOnCheckListener(new MyAdapter.OnCheckListener() {
            @Override
            public void onCheckListener(int count) {
                tvAddNum.setText("导入书架("+count+")");
            }

            @Override
            public void onCheckListener(List<File> list) {
                checkList.clear();
                checkList.addAll(list);
            }
        });
        tvAddNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int k=0;k<checkList.size();k++){
                    List<File> myBooks = new ArrayList<File>();
                    myBooks.add(checkList.get(k));
                    Book myBook = new Book();
                    myBook.setBookName(checkList.get(k).getName());
                    myBook.setBookLocal("1");
                    dao.insert(myBook);
                    importBooks(myBooks,myBook.getId());
                }
            }
        });
        tvAllChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> mylist = dao.loadAll();
//                if(mylist!=null&&mylist.size()>0){
//                    for(int i=0;i<mylist.size();i++){
//                        Log.e("zy","---------------->"+mylist.get(i).getId());
//                    }
//                }
                CustomDialog dialog = new CustomDialog(ImportBooksActivity.this);
                dialog.show();
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
                        booleanList.add(false);
                        adapter.notifyDataSetChanged();
                        tvCount.setText("一共"+files.size()+"txt个文件");
                    }

                    @Override
                    public void onComplete() {
                        llCount.setVisibility(View.GONE);
                        llBar.setVisibility(View.VISIBLE);
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
                        Log.e("zy","------------>成功是");
                    }
                });
    }
}
