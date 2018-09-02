package com.read;

import com.example.tunsh.greendaodemo.Chapter;

import java.util.List;

/**
 * Name: ReadView
 * Author: tunsh
 * Date: 2017-12-01 16:45
 */
public interface ReadView {
    void showContent(ReadAdapter readAdapter,int currentChapter);
    void currentChapterNum(int num);
    void getchapterCount(int num);
    void getChapters(List<Chapter> list);
}
