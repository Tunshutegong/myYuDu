package com.activity.menu.bookstacks;

import java.util.List;

/**
 * Name: BookRankResult
 * Author: tunsh
 * Date: 2017-12-12 14:12
 */
public class BookRankResult {
    private Ranking ranking;
    private boolean ok;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public class Ranking {
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        private List<Books> books;

        public List<Books> getBooks() {
            return books;
        }

        public void setBooks(List<Books> books) {
            this.books = books;
        }
    }


    public class Books {
        private String _id;
        private String title;
        private String author;
        private String shortIntro;
        private String cover;
        private String latelyFollower;
        private String majorCate;

        public String getMajorCate() {
            return majorCate;
        }

        public void setMajorCate(String majorCate) {
            this.majorCate = majorCate;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getShortIntro() {
            return shortIntro;
        }

        public void setShortIntro(String shortIntro) {
            this.shortIntro = shortIntro;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getLatelyFollower() {
            return latelyFollower;
        }

        public void setLatelyFollower(String latelyFollower) {
            this.latelyFollower = latelyFollower;
        }
    }
}
