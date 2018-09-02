package com.utils;

/**
 * description:接口
 * Created by ZYM on 2017/1/9.
 * copyright www.jiehun.com.cn
 */

public class Api {
    /*
    * 图片公共接口http://statics.zhuishushenqi.com/icon/奇幻_.pn
    * */
    public static final String IMG_BASE_URL = "http://statics.zhuishushenqi.com";
    /*
    * 书库分类接口
    * */
    public static String STACKS_INFO = "http://api.zhuishushenqi.com/cats/lv2/statistics";
    /*
    * 书荒互助最新，最热 公共接口
    * */
    public static String API_BASE_URL = "http://api.zhuishushenqi.com/post/help?duration=all";
    public static String BookShort = "http://api.zhuishushenqi.com/post/help?duration=all&sort=created&start=0&limit=20&distillate=";
    /*
    * 拼接url
    * sort 分类，start 页数
    * */
    public static String getApiUrl(String sort, int start){
        String apiUrl = API_BASE_URL+"&"+"sort="+sort+"&"+"start="+start+"&limit=10&distillate=";
        return apiUrl;
    }

    /*
    * 小说详情公共接口
    *
    * */
    public static String BOOKS = "http://api.zhuishushenqi.com/book/";

    /*
    * 小说详情
    * */
    public static String getNovelDetailUrl(String bookId){
        String bookUrl = BOOKS+bookId;
        return bookUrl;
    }
    /*
    * 评论
    * */
    public static String BOOK_COMMENT = "http://api.zhuishushenqi.com/post/review/best-by-book?book=";
    /*
    * 推荐书单
    * */
    public static String BOOK_LIST = "http://api.zhuishushenqi.com/book-list/5831c3b5a66586505d66e397/recommend?limit=3";
    /*
    * 书籍分类列表
    * */
    public static String BOOK_CATE_LIST = "http://api.zhuishushenqi.com/book/by-categories?gender=male&type=hot&major=玄幻&minor=&start=0&limit=20";
    public static String BOOK_CLASS_LIST = "http://api.zhuishushenqi.com/book/by-categories?";
    /*major 分类  玄幻  奇幻
    *page 页数
    * type 类型  hot new
    * minor 更深一级分类 major下的分类
    * gender  性别
    * */
    public static String getBookCateListUrl(String major, String page, String type, String minor, String gender){
        String url = BOOK_CLASS_LIST+"gender="+gender+"&type="+type+"&major="+major+"&minor="+minor+"&start="+page+"&limit=20";
        return url;
    }
    /*
    * 书籍类别
    * */
    public static String BOOK_CLASS = "http://api.zhuishushenqi.com/cats/lv2";
    /*
    * 书籍评论
    * */
    public static String getCommentUrl(String bookId){
        String commentUrl = BOOK_COMMENT+bookId;
        return commentUrl;
    }
    /*
    * 搜索热词
    * */
    public static String HOT_WORD = "http://api.zhuishushenqi.com/book/hot-word";
    /*
    * 精选 页面
    * */
    public static String JING_XUAN_TAG = "https://api.ireadercity.com:4430/c/main/GetJingXuanTagInfo?at=0&mac=84%3A9f%3Ab5%3Aa2%3A81%3A5c&did=389276f33c111c14de6534d58630a18c&height=1920&idfa=389276f33c111c14de6534d58630a18c&ReqType=1&av=5.31.1&dtp=Android&TagId=22&ov=7.0&lang=zh&width=1080&dmd=MHA-AL00&chn=aireader_huawei";
    public static String JING_XUAN_DETAILS = "https://api.ireadercity.com:4430/c/main/GetJingXuanTagInfo?at=0&mac=84%3A9f%3Ab5%3Aa2%3A81%3A5c&did=389276f33c111c14de6534d58630a18c&height=1920&idfa=389276f33c111c14de6534d58630a18c&ReqType=0&av=5.31.1&dtp=Android&TagId=22&ov=7.0&lang=zh&width=1080&dmd=MHA-AL00&chn=aireader_huawei";
    /*
    * 排行榜
    * */
    public static String PAI_HANG_BANG = "http://api.zhuishushenqi.com/ranking/54d42d92321052167dfb75e3";
    /*
    * 综合讨论区  全部distillate=是精品distillate=true，最多评论sort=comment-count最新评论sort=created
    * */
    public static String ZONG_HE_TAO_LUN_QU = "http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=";
    public static String zongHeTaoLunUrl(String block,String dis,String sort,int start){
        /*
        * 综合讨论区 ramble，原创区original 女生去 girl
        * */
        String url = "http://api.zhuishushenqi.com/post/by-block?block="+block+"&duration=all&sort="+sort+"&type=all&start="+start+"&limit=20&distillate="+dis;
        return url;
    }
    //精选中
    //精选
    public static String JINGXUANBASE = "http://api.zhuishushenqi.com/ranking/";
    //精选1
    public static String JINGXUAN1 = "564d8003aca44f4f61850fcd";
    public static String JINGXUANMAN = "564d80457408cfcd63ae2dd0";
    public static String JINGXUANGIRL = "564d81151109835664770ad7";
    //目录
    public static String getChapterUrl(String id){
        //http://api.zhuishushenqi.com/mix-atoc/5a0044555fa0a2fb54e06c6d?view=chapters
        String s = "http://api.zhuishushenqi.com/mix-atoc/"+id+"?view=chapters";
        return s;
    }
    //dianping 0
    public static String getCommentListUrl(int start,String bookId){
        String s = "http://api.zhuishushenqi.com/post/review/by-book?book="+bookId+"&sort=updated&start="+start+"&limit=20";
        return s;
    }
    public static String getCommentDetailListUrl(int start,String bookId){
        String s = "http://api.zhuishushenqi.com/post/review/"+bookId+"/comment?start="+start+"&limit=20";
        return s;
    }

    public static String getBookListUrl(String major,String minor,int start){
        String s = "http://api.zhuishushenqi.com/book/by-categories?gender=male&type=over&major="+major+"&minor="+minor+"&start="+start+"0&limit=20";
        return s;
    }
}
