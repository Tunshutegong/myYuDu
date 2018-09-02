package com.activity.menu;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.activity.menu.bookshelf.BookShelfFragment;
import com.activity.menu.bookstacks.BookStacksFragment;
import com.activity.menu.jingxuan.JingXuanFragment;
import com.activity.menu.mine.MineFragment;
import com.example.tunsh.greendaodemo.R;

import java.util.List;

/**
 * Name: MenuPersenter
 * Author: tunsh
 * Date: 2017-12-04 11:29
 */
public class MenuPersenter {
    private Context context;
    private MenuView view;
    private FragmentManager manager;
    private int mContentId;
    public MenuPersenter(Context context,MenuView view){
        this.context = context;
        this.view = view;
    }
    public void init(FragmentManager fragmentManager,  int contentContainerId){
        manager = fragmentManager;
        mContentId = contentContainerId;
    }

    public boolean getItemSelected(int id){
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        Fragment selectedFragment = manager.findFragmentByTag(String.valueOf(id));
        switch (id){
            case R.id.tab_bookcase:
                if (selectedFragment == null) {
                    selectedFragment = new BookShelfFragment();
                    fragmentTransaction.add(mContentId, selectedFragment, String.valueOf(id));
                }
                checkedTab(fragmentTransaction, id);
                return true;
            case R.id.tab_jingxuan:
                if (selectedFragment == null) {
                    selectedFragment = new JingXuanFragment();
                    fragmentTransaction.add(mContentId, selectedFragment, String.valueOf(id));
                }
                checkedTab(fragmentTransaction, id);
                return true;
            case R.id.tab_explore:
                if (selectedFragment == null) {
                    selectedFragment = new BookStacksFragment();
                    fragmentTransaction.add(mContentId, selectedFragment, String.valueOf(id));
                }
                checkedTab(fragmentTransaction, id);
                return true;
            case R.id.tab_mine:
                if (selectedFragment == null) {
                    selectedFragment = new MineFragment();
                    fragmentTransaction.add(mContentId, selectedFragment, String.valueOf(id));
                }
                checkedTab(fragmentTransaction, id);
                return true;
            default:
            return false;
        }
    }

    private void checkedTab(FragmentTransaction fragmentTransaction, @IdRes int tabId) {
        List<Fragment> fragments = manager.getFragments();
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                if (fragment.getTag().equals(String.valueOf(tabId))) {
                    fragmentTransaction.show(fragment);
                } else {
                    fragmentTransaction.hide(fragment);
                }
            }
        }
        fragmentTransaction.commit();
    }
}
