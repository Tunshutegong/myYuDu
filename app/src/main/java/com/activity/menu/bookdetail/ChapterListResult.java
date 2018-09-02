package com.activity.menu.bookdetail;

import java.util.List;

/**
 * Name: ChapterListResult
 * Author: tunsh
 * Date: 2017-12-13 13:45
 */
public class ChapterListResult {
    private MixTop mixToc;

    public MixTop getMixToc() {
        return mixToc;
    }

    public void setMixToc(MixTop mixToc) {
        this.mixToc = mixToc;
    }

    public class MixTop{
        private String _id;
        private String book;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getBook() {
            return book;
        }

        public void setBook(String book) {
            this.book = book;
        }

        public List<Chapters> getChapters() {
            return chapters;
        }

        public void setChapters(List<Chapters> chapters) {
            this.chapters = chapters;
        }

        private List<Chapters> chapters;
        public class Chapters{
            private String title;
            private String link;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }
    }

}
