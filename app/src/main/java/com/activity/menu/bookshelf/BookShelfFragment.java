package com.activity.menu.bookshelf;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.menu.GideActivity;
import com.activity.menu.PermissionListerner;
import com.activity.menu.bookshelf.adapter.BookCaseAdapter;
import com.activity.menu.bookshelf.adapter.LikeBook;
import com.activity.menu.importbooks.ImportBooksActivity;
import com.activity.menu.mine.ProgressWheel;
import com.afa.tourism.greendao.gen.BookDao;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemChildLongClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.tunsh.greendaodemo.AFaApplication;
import com.example.tunsh.greendaodemo.Book;
import com.example.tunsh.greendaodemo.R;
import com.example.tunsh.greendaodemo.ReadActivity;
import com.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Name: BookShelfFragment
 * Author: tunsh
 * Date: 2017-12-04 11:22
 */
public class BookShelfFragment extends Fragment   {
    private LinearLayout llEt,llTitle;
    private RecyclerView rclv;
    private BaseQuickAdapter adapter;
    List<Book> list = new ArrayList<>();
    private ItemTouchHelper itemTouchHelper;
    private BookCaseAdapter bookCaseAdapter;
    private View headView;
    private TextView tvAddBook,tvCount,tvEtid,tvOver;
    private BookDao dao;
    private View editBarView;
    private OnBookCaseEditListener mEditListener;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_shelf_fragment_layout, container, false);
        headView = inflater.inflate(R.layout.book_shelf_head_view_layout,null);
//        editBarView = inflater.inflate(R.layout.edit_bar_view_layout,getActivity(),false)
        dao = AFaApplication.getApplication().getDaoSession().getBookDao();
        llEt = (LinearLayout)view.findViewById(R.id.llEt);
        llTitle = (LinearLayout)view.findViewById(R.id.llTitle);
        rclv = (RecyclerView)view.findViewById(R.id.rclv);
        tvAddBook = (TextView)view.findViewById(R.id.tvAddBook);
        tvOver = (TextView)view.findViewById(R.id.tvOver);
        tvEtid = (TextView)view.findViewById(R.id.tvEtid);
        rclv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rclv.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        list.clear();
        final List<Book> mylist = dao.loadAll();
        if(mylist!=null){
            list.addAll(mylist);
        }
        bookCaseAdapter = new BookCaseAdapter(list);
        bookCaseAdapter.setEmptyView(R.layout.view_empty_book_shelf, rclv);
        bookCaseAdapter.bindToRecyclerView(rclv);
        ((SimpleItemAnimator)rclv.getItemAnimator()).setSupportsChangeAnimations(false);
        rclv.setAdapter(bookCaseAdapter);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback((BaseItemDraggableAdapter) bookCaseAdapter);
        itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(rclv);
        bookCaseAdapter.enableDragItem(itemTouchHelper);
        rclv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                String str = bookCaseAdapter.getItem(position).getBookName();
                Intent intent = new Intent(getActivity(),ReadActivity.class);
                intent.putExtra("bookName", bookCaseAdapter.getItem(position).getBookName());
                startActivity(intent);
            }
        });
        bookCaseAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, final int position) {
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        itemTouchHelper.startDrag(rclv.findViewHolderForLayoutPosition(position));
                    }
                }, 200);
                return true;
            }
        });

        tvAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),ImportBooksActivity.class));
            }
        });
        tvEtid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookCaseAdapter.openEdit(true);
                bookCaseAdapter.notifyDataSetChanged();
                llEt.setVisibility(View.VISIBLE);
                llTitle.setVisibility(View.GONE);
                if (mEditListener != null) {
                    getEditBarView(mEditListener.getBottomGroup()).setVisibility(View.VISIBLE);
                }
            }
        });
        tvOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookCaseAdapter.openEdit(false);
                bookCaseAdapter.notifyDataSetChanged();
                llEt.setVisibility(View.GONE);
                llTitle.setVisibility(View.VISIBLE);
                if (mEditListener != null) {
                    getEditBarView(mEditListener.getBottomGroup()).setVisibility(View.GONE);
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBookCaseEditListener) {
            mEditListener = (OnBookCaseEditListener) context;
        }
    }

    public interface OnBookCaseEditListener {
        ViewGroup getBottomGroup();
    }

    public View getEditBarView(ViewGroup viewGroup){
        if (editBarView == null) {
            editBarView = LayoutInflater
                    .from(viewGroup.getContext())
                    .inflate(R.layout.edit_bar_view_layout, viewGroup, false);
            viewGroup.addView(editBarView);
        }
        return editBarView;
    }
}
