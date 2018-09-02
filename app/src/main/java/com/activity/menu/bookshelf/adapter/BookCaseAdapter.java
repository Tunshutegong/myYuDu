package com.activity.menu.bookshelf.adapter;

import android.view.View;
import android.widget.Toast;

import com.example.tunsh.greendaodemo.Book;
import com.example.tunsh.greendaodemo.R;

import java.util.List;

/**
 * Name: BookCaseAdapter
 * Author: tunsh
 * Date: 2017-12-06 11:39
 */
public class BookCaseAdapter extends CommonItemDraggableAdapter<Book> {
    private boolean isChosed = false;
    private boolean isEdit = false;
    public BookCaseAdapter(List<Book> data) {
        super(R.layout.book_shelf_item_layout, data);
    }

    @Override
    protected void convert(CommonViewHolder helper, Book item) {
        helper.setText(R.id.tvBookName,item.getBookName());
        helper.setGone(R.id.imgChose,isEdit);
        helper.addOnLongClickListener(R.id.img);
    }

    public void openEdit(boolean isOpen){
        isEdit = isOpen;
    }
}
