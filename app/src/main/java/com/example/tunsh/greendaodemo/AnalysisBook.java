package com.example.tunsh.greendaodemo;

import android.util.Log;

import org.mozilla.universalchardet.UniversalDetector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Name: AnalysisBook
 * Author: tunsh
 * Date: 2017-10-26 16:07
 */
public class AnalysisBook {
    public static AnalysisBook getInstance() {
        return new AnalysisBook();
    }

    public Observable<LocBook> analysisBook(final File book, final Long id) {
        return Observable.create(new ObservableOnSubscribe<LocBook>(){

            @Override
            public void subscribe(ObservableEmitter<LocBook> e) throws Exception {
                saveChapter(book,id);
                e.onComplete();
//                e.onNext(new LocBook(true,book));
            }
        });
    }

    private void saveChapter(File book,Long id) throws IOException {
        String regex = "第.{1,7}[章|节].{0,}";

        String encoding;

        FileInputStream fis = new FileInputStream(book);
        byte[] buf = new byte[4096];
        UniversalDetector detector = new UniversalDetector(null);
        int nread;
        while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
        detector.dataEnd();
        encoding = detector.getDetectedCharset();
        if (encoding == null || encoding.length() == 0)
            encoding = "utf-8";
        fis.close();
        fis = null;

        int chapterPageIndex = 0;
        String title = null;
        StringBuilder contentBuilder = new StringBuilder();
        fis = new FileInputStream(book);
        InputStreamReader inputreader = new InputStreamReader(fis, encoding);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        while ((line = buffreader.readLine()) != null) {

            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(line);
            if (m.find()) {
                if (contentBuilder.toString().length() > 0) {
                    if(contentBuilder.toString().replaceAll("　","").trim().length()>0){
                        Log.e("yy","------->"+title);
                        saveDurChapterContent(id,chapterPageIndex, title, contentBuilder.toString());
                        chapterPageIndex++;
                    }
                    contentBuilder.delete(0, contentBuilder.length());
                }
                title = line.trim();
            } else {
                if (line.trim().length() == 0) {
                    if (contentBuilder.length() > 0) {
                        contentBuilder.append("\r\n\u3000\u3000");
                    } else {
                        contentBuilder.append("\r\u3000\u3000");
                    }
                } else {
                    contentBuilder.append(line);
                    if (title == null) {
                        title = line.trim();
                    }
                }
            }
        }
        if (contentBuilder.length() > 0) {
            saveDurChapterContent(id,chapterPageIndex, title, contentBuilder.toString());
            contentBuilder.delete(0, contentBuilder.length());
            title = null;
        }
        buffreader.close();
        inputreader.close();
        fis.close();
        fis = null;
    }

    public void saveDurChapterContent(Long id,int chapterPageIndex,String title,String content){
        Chapter chapter = new Chapter();
        chapter.setChapterContent(content);
        chapter.setChapterId(id);
        chapter.setChapterName(title);
        AFaApplication.getApplication().getDaoSession().getChapterDao().insert(chapter);

    }
}
