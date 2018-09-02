package com.example.tunsh.greendaodemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Name: Chapter
 * Author: tunsh
 * Date: 2017-10-25 14:19
 */
@Entity
public class Chapter {
    private Long id;
    private Long chapterId;
    private String chapterName;
    private String chapterContent;
    private String chapterContentUrl;//本章节的内容链接
    public String getChapterContent() {
        return this.chapterContent;
    }
    public void setChapterContent(String chapterContent) {
        this.chapterContent = chapterContent;
    }
    public String getChapterName() {
        return this.chapterName;
    }
    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getChapterId() {
        return this.chapterId;
    }
    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }
    public String getChapterContentUrl() {
        return this.chapterContentUrl;
    }
    public void setChapterContentUrl(String chapterContentUrl) {
        this.chapterContentUrl = chapterContentUrl;
    }
    @Generated(hash = 1098339926)
    public Chapter(Long id, Long chapterId, String chapterName,
            String chapterContent, String chapterContentUrl) {
        this.id = id;
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.chapterContent = chapterContent;
        this.chapterContentUrl = chapterContentUrl;
    }
    @Generated(hash = 393170288)
    public Chapter() {
    }
}
