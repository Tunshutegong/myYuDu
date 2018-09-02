package com.activity.menu.bookdetail;

/**
 * Name: BookDetailResult
 * Author: tunsh
 * Date: 2017-12-12 17:10
 */
public class BookDetailResult {
    private String title;
    private String _id;
    private String author;
    private String longIntro;
    private String cover;
    private String lastChapter;
    private String wordCount;
    private String minorCate;
    private String retentionRatio;
    private String latelyFollower;

    public String getLatelyFollower() {
        return latelyFollower;
    }

    public void setLatelyFollower(String latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public String getSerializeWordCount() {
        return serializeWordCount;
    }

    public void setSerializeWordCount(String serializeWordCount) {
        this.serializeWordCount = serializeWordCount;
    }

    private String serializeWordCount;

    public String getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(String retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public String getMinorCate() {
        return minorCate;
    }

    public void setMinorCate(String minorCate) {
        this.minorCate = minorCate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLongIntro() {
        return longIntro;
    }

    public void setLongIntro(String longIntro) {
        this.longIntro = longIntro;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public String getWordCount() {
        return wordCount;
    }

    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }
}
