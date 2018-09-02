package com.example.tunsh.greendaodemo;

import org.greenrobot.greendao.annotation.Entity;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.DaoException;
import com.afa.tourism.greendao.gen.DaoSession;
import com.afa.tourism.greendao.gen.BookDao;
import com.afa.tourism.greendao.gen.ChapterDao;

/**
 * Name: Book
 * Author: tunsh
 * Date: 2017-10-25 14:18
 */
@Entity
public class Book {
    @Id(autoincrement = true)
    private Long id;
    private String bookName;
    private String bookAuthor;
    private String last;
    private String lasttime;//最后一次阅读
    private String currentChapterName;//当前章名字
    private String currentNum;//当前正阅读第几章
    private String bookCoverUrl;//书籍封面url
    private String bookLocal;//这本书是本地的还是网络的1是本地2是网络
    private String isOver;//这本书是不是看完了1是看完2是没看完
    private String readNum;//这本书阅读了多少次
    private String currentChapterPage;//看到当前章的第几页
    private String netBookId;//网络小说的id用来判断这本书是否在书架中

    @ToMany(referencedJoinProperty = "chapterId")
    private List<Chapter> chapters;
    /** Used for active entity operations. */
    @Generated(hash = 1097957864)
    private transient BookDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    public String getBookAuthor() {
        return this.bookAuthor;
    }
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    public String getBookName() {
        return this.bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 936914273)
    public synchronized void resetChapters() {
        chapters = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 429544152)
    public List<Chapter> getChapters() {
        if (chapters == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ChapterDao targetDao = daoSession.getChapterDao();
            List<Chapter> chaptersNew = targetDao._queryBook_Chapters(id);
            synchronized (this) {
                if(chapters == null) {
                    chapters = chaptersNew;
                }
            }
        }
        return chapters;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1115456930)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBookDao() : null;
    }
    public String getLast() {
        return this.last;
    }
    public void setLast(String last) {
        this.last = last;
    }
    public String getCurrentChapterPage() {
        return this.currentChapterPage;
    }
    public void setCurrentChapterPage(String currentChapterPage) {
        this.currentChapterPage = currentChapterPage;
    }
    public String getReadNum() {
        return this.readNum;
    }
    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }
    public String getIsOver() {
        return this.isOver;
    }
    public void setIsOver(String isOver) {
        this.isOver = isOver;
    }
    public String getBookLocal() {
        return this.bookLocal;
    }
    public void setBookLocal(String bookLocal) {
        this.bookLocal = bookLocal;
    }
    public String getBookCoverUrl() {
        return this.bookCoverUrl;
    }
    public void setBookCoverUrl(String bookCoverUrl) {
        this.bookCoverUrl = bookCoverUrl;
    }
    public String getCurrentNum() {
        return this.currentNum;
    }
    public void setCurrentNum(String currentNum) {
        this.currentNum = currentNum;
    }
    public String getCurrentChapterName() {
        return this.currentChapterName;
    }
    public void setCurrentChapterName(String currentChapterName) {
        this.currentChapterName = currentChapterName;
    }
    public String getLasttime() {
        return this.lasttime;
    }
    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }
    public String getNetBookId() {
        return this.netBookId;
    }
    public void setNetBookId(String netBookId) {
        this.netBookId = netBookId;
    }
    @Generated(hash = 1426679469)
    public Book(Long id, String bookName, String bookAuthor, String last, String lasttime,
            String currentChapterName, String currentNum, String bookCoverUrl, String bookLocal,
            String isOver, String readNum, String currentChapterPage, String netBookId) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.last = last;
        this.lasttime = lasttime;
        this.currentChapterName = currentChapterName;
        this.currentNum = currentNum;
        this.bookCoverUrl = bookCoverUrl;
        this.bookLocal = bookLocal;
        this.isOver = isOver;
        this.readNum = readNum;
        this.currentChapterPage = currentChapterPage;
        this.netBookId = netBookId;
    }
    @Generated(hash = 1839243756)
    public Book() {
    }

}
