package com.activity.menu.bookclassify;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * Created by ZYM on 2017/1/22.
 * copyright www.jiehun.com.cn
 */

public class BookCateResult implements Serializable {
    private List<CateResult> male;
    private List<CateResult> female;
    private List<CateResult> press;

    public List<CateResult> getMale() {
        return male;
    }

    public void setMale(List<CateResult> male) {
        this.male = male;
    }

    public List<CateResult> getFemale() {
        return female;
    }

    public void setFemale(List<CateResult> female) {
        this.female = female;
    }

    public List<CateResult> getPress() {
        return press;
    }

    public void setPress(List<CateResult> press) {
        this.press = press;
    }

    public class CateResult implements Serializable{
        private String major;
        private ArrayList<String> mins;

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public ArrayList<String> getMins() {
            return mins;
        }

        public void setMins(ArrayList<String> mins) {
            this.mins = mins;
        }
    }
}
