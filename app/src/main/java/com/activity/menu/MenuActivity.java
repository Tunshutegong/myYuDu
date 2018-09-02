package com.activity.menu;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.activity.menu.bookshelf.BookShelfFragment;
import com.example.tunsh.greendaodemo.R;
import com.utils.BottomNavigationViewHelper;

public class MenuActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,MenuView,BookShelfFragment.OnBookCaseEditListener {
    private BottomNavigationView bottomNavigation;
    private int selectedTabId;
    private MenuPersenter persenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        persenter = new MenuPersenter(this,this);
        persenter.init(getSupportFragmentManager(),R.id.content_view);
        if(savedInstanceState!=null) {
            savedInstanceState.getInt("selectedTabId", R.id.tab_bookcase);
        }else {
            persenter.getItemSelected(R.id.tab_bookcase);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return persenter.getItemSelected(item.getItemId());
    }

    @Override
    public ViewGroup getBottomGroup() {
        return bottomNavigation;
    }
}
