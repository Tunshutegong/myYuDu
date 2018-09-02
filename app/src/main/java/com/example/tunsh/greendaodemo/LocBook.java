package com.example.tunsh.greendaodemo;

/**
 * Name: LocBook
 * Author: tunsh
 * Date: 2017-10-26 16:01
 */
public class LocBook {

    private boolean isNew;
    private Book book;

    public LocBook(boolean isNew,Book book){
        this.book = book;
        this.isNew = isNew;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
