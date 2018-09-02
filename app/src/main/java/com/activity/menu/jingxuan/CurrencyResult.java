package com.activity.menu.jingxuan;

import java.util.List;

/**
 * Name: CurrencyResult
 * Author: tunsh
 * Date: 2017-12-11 17:14
 */
public class CurrencyResult {
    private List<Posts> posts;

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }

    public class Posts{
        private BookShortResult.Helps.Author author;
        private String _id;
        private String likeCount;
        private String state;
        private String updated;
        private String created;
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public BookShortResult.Helps.Author getAuthor() {
            return author;
        }

        public void setAuthor(BookShortResult.Helps.Author author) {
            this.author = author;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }
    }
}
