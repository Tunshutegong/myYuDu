package com.activity.menu;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afa.tourism.greendao.gen.BookDao;
import com.afa.tourism.greendao.gen.ChapterDao;
import com.afa.tourism.greendao.gen.DaoMaster;
import com.example.tunsh.greendaodemo.Book;
import com.example.tunsh.greendaodemo.Chapter;
import com.example.tunsh.greendaodemo.R;
import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.utils.MySQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GideActivity extends BaseActivity {
    private AppCompatImageView iv_smile;
    private DaoMaster daoMaster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        LinearLayout llWelcome = (LinearLayout) findViewById(R.id.ll_welcome);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_in);
        iv_smile = (AppCompatImageView)findViewById(R.id.iv_smile);
        TextView tvDate = findViewById(R.id.tv_date);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日，EEEE");
        tvDate.setText(format2.format(new Date()));
        llWelcome.startAnimation(animation);
        iv_smile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        MigrationHelper.DEBUG = true;
//
//        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this, "notes-db",
//                null);
//        daoMaster = new DaoMaster(helper.getWritableDatabase());
//        Book book = new Book();
//        BookDao bookDao = daoMaster.newSession().getBookDao();
//        Chapter chapter = new Chapter();
//        ChapterDao chapterDao = daoMaster.newSession().getChapterDao();
//        chapterDao.insert(chapter);
//        bookDao.insert(book);



        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(GideActivity.this,MenuActivity.class);
                startActivity(intent);
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ,Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionListerner() {
                    @Override
                    public void onGranted() {
//                        Toast.makeText(GideActivity.this,"权限全部同意了",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDenid(List<String> denidPermission) {
                        for (String permission:denidPermission){
                            Toast.makeText(GideActivity.this,"权限被拒绝:"+permission,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                finish();
            }
        }, 3000);
    }
}
