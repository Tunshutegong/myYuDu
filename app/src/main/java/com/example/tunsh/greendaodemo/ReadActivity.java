package com.example.tunsh.greendaodemo;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.menu.BaseActivity;
import com.afa.tourism.greendao.gen.BookDao;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.pageview.PageLoaderAdapter;
import com.pageview.PageView;
import com.read.BookChapterAdapter;
import com.read.ReadAdapter;
import com.read.ReadPersenter;
import com.read.ReadView;
import com.read.setting.ReadTheme;
import com.read.setting.ReaderSettingDialog;
import com.read.setting.ReaderSettingManager;
import com.utils.AppManager;
import com.utils.NoFastClickUtils;
import com.utils.RecyclerViewItemDecoration;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ReadActivity extends BaseActivity implements ReadView,View.OnClickListener{
    private PageView pv_read;
    private BookDao dao;
    private ReadPersenter persenter;
    private View readBottom;
    private TextView tvNextPage,tvPrePage,tvSetting,read_tv_category;
    private int currentReading = 0;
    private int chapterCount;
    private Animation mTopInAnim;
    private Animation mTopOutAnim;
    private Animation mBottomInAnim;
    private Animation mBottomOutAnim;
    private AppBarLayout appBar;
    private SeekBar seekBar;
    private BottomSheetDialog mReadSettingDialog;
    private String  bookName;
    private TextView read_tv_night_mode;
    private RecyclerView recyclerViewChapter;
    private BookChapterAdapter adapter;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        dao = AFaApplication.getApplication().getDaoSession().getBookDao();
        pv_read = (PageView) findViewById(R.id.pv_read);
        appBar = (AppBarLayout)findViewById(R.id.appbar);
        readBottom = findViewById(R.id.read_bottom);
        seekBar = (SeekBar) findViewById(R.id.read_sb_chapter_progress);
        seekBar.setEnabled(true);
        tvNextPage = (TextView)findViewById(R.id.read_tv_next_chapter);
        tvPrePage = (TextView)findViewById(R.id.read_tv_pre_chapter);
        tvSetting = (TextView)findViewById(R.id.read_tv_setting);
        read_tv_night_mode = (TextView)findViewById(R.id.read_tv_night_mode);
        read_tv_category = (TextView)findViewById(R.id.read_tv_category);
        recyclerViewChapter = (RecyclerView)findViewById(R.id.read_rv_section);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        tvNextPage.setOnClickListener(this);
        tvPrePage.setOnClickListener(this);
        tvSetting.setOnClickListener(this);
        read_tv_night_mode.setOnClickListener(this);
        read_tv_category.setOnClickListener(this);
        recyclerViewChapter.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewChapter.addItemDecoration(new RecyclerViewItemDecoration.Builder(this)
                .color( Color.argb(77,97,97,97))
                .thickness(1)
                .create());
        bookName = getIntent().getStringExtra("bookName");
        if(bookName == null){
            bookName = "";
        }
        persenter = new ReadPersenter(this,this);
        ReaderSettingManager.init(this);
        persenter.getBook(currentReading,bookName);
        pv_read.setTouchListener(new PageView.TouchListener() {
            @Override
            public void center() {
                toggleMenu();
            }

            @Override
            public void cancel() {

            }
        });

        pv_read.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(appBar.getVisibility()==View.VISIBLE){
                    hideReadMenu();
                    return true;
                }
                return false;
            }
        });
        setListener();
    }
    private boolean hideReadMenu() {
        if (appBar.getVisibility() == VISIBLE) {
            toggleMenu();
            return true;
        }
        return false;
    }
    private void toggleMenu() {
        initMenuAnim();

        if (appBar.getVisibility() == VISIBLE) {
            //关闭
            appBar.startAnimation(mTopOutAnim);
            readBottom.startAnimation(mBottomOutAnim);
            readBottom.setVisibility(GONE);
            appBar.setVisibility(GONE);
        } else {
            readBottom.setVisibility(VISIBLE);
            appBar.setVisibility(VISIBLE);
            readBottom.startAnimation(mBottomInAnim);
            appBar.startAnimation(mTopInAnim);
//            boolean isNight = ReadTheme.getReadTheme(readView.getPageBackground(), readView.getTextColor()) == ReadTheme.NIGHT;
//            readTvNightMode.setSelected(isNight);
//            readTvNightMode.setText(isNight ? getString(R.string.read_daytime) : getString(R.string.read_night));
//            showSystemBar();
        }
    }

    //初始化菜单动画
    private void initMenuAnim() {
        if (mTopInAnim != null) return;

        mTopInAnim = AnimationUtils.loadAnimation(this, R.anim.slide_top_in);
        mTopOutAnim = AnimationUtils.loadAnimation(this, R.anim.slide_top_out);
        mBottomInAnim = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_in);
        mBottomInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                pv_read.setCanTouch(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mBottomOutAnim = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_out);
        mBottomOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                pv_read.setCanTouch(true);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //退出的速度要快
        mTopOutAnim.setDuration(200);
        mBottomOutAnim.setDuration(200);
    }

    @Override
    public void showContent(ReadAdapter readAdapter,int currentChapter) {
        pv_read.setAdapter(readAdapter);
        pv_read.openSection(currentChapter, 0);//显示当前章和当前页
    }

    @Override
    public void currentChapterNum(int num) {

    }

    @Override
    public void getchapterCount(int num) {
        chapterCount = num;
        seekBar.setMax(num);
    }

    @Override
    public void getChapters(List<Chapter> list) {
        adapter = new BookChapterAdapter(list);
        setAdapterCheckPos();
        recyclerViewChapter.setAdapter(adapter);
    }

    public void setAdapterCheckPos(){
        recyclerViewChapter.scrollToPosition(currentReading);
        adapter.setCurrentChapter(currentReading);
        adapter.notifyDataSetChanged();
    }

    public void setListener(){
        recyclerViewChapter.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                currentReading = position;
                pv_read.openSection(currentReading, 0);
                drawerLayout.closeDrawers();
                setAdapterCheckPos();
            }
        });
        pv_read.setOnThemeChangeListener(new PageView.OnThemeChangeListener() {
            @Override
            public void onThemeChange(int textColor, int backgroundColor, int textSize) {
                recyclerViewChapter.setBackgroundColor(backgroundColor);
                if(adapter!=null){
                    adapter.setTextColor(textColor);
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentReading = i;
                pv_read.openSection(currentReading, 0);
                setAdapterCheckPos();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.read_tv_next_chapter:
                if(NoFastClickUtils.isFastClick()){
                    Toast.makeText(ReadActivity.this, "点击太快", Toast.LENGTH_SHORT).show();
                }else {

                    if(currentReading<=chapterCount){
                        currentReading++;
//                        persenter.preOrnextChapter(currentReading);
                        pv_read.openSection(currentReading, 0);
                        seekBar.setProgress(currentReading);
                        setAdapterCheckPos();
                    }
                }

                break;
            case R.id.read_tv_pre_chapter:
                if(NoFastClickUtils.isFastClick()){
                    Toast.makeText(ReadActivity.this, "点击太快", Toast.LENGTH_SHORT).show();
                }else {

                    if(currentReading>0){
                        currentReading--;
                        pv_read.openSection(currentReading, 0);
                        seekBar.setProgress(currentReading);
                        setAdapterCheckPos();
                    }
                }
                break;
            case R.id.read_tv_setting:
                toggleMenu();
                if (mReadSettingDialog == null) {
                    mReadSettingDialog = new ReaderSettingDialog(ReadActivity.this, pv_read);
                }
                mReadSettingDialog.show();
                break;
            case R.id.read_tv_night_mode:
                break;
            case R.id.read_tv_category:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            default:
        }
    }

}
