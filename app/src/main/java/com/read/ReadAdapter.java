package com.read;

import android.util.SparseArray;

import com.example.tunsh.greendaodemo.Chapter;

import java.util.List;

/**
 * Name: ReadAdapter
 * Author: tunsh
 * Date: 2017-12-01 15:10
 */
public class ReadAdapter extends StringAdapter {

    private SparseArray<Chapter> bookArray;

    public ReadAdapter() {
        bookArray = new SparseArray<>();
    }
    @Override
    public boolean hasNextSection(int currentSection) {
        return bookArray.get(currentSection + 1) != null;
    }

    @Override
    public boolean hasPreviousSection(int currentSection) {
        return bookArray.get(currentSection - 1) != null;
    }

    @Override
    protected String getPageSource(int section) {
        Chapter sectionContent = bookArray.get(section);
        return sectionContent != null ? bookArray.get(section).getChapterContent() : null;
    }

    @Override
    public int getSectionCount() {
        return bookArray.size();
    }

    @Override
    public String getSectionName(int section) {
        Chapter sectionContent = bookArray.get(section);
        return sectionContent != null ? bookArray.get(section).getChapterName() : null;
    }

    public void addData(int section, Chapter content) {
        bookArray.put(section, content);
    }
    public boolean hasSection(int section) {
        return bookArray.get(section) != null;
    }
    public void clear(){
        bookArray.clear();
    }
}
