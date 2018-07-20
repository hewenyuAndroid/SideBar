package com.hwy.sidebar.bean;

/**
 * 作者: hewenyu
 * 日期: 2018/7/19 18:18
 * 说明: 封装排序的数据的Bean，需要记录字符串的首字母
 */
public class SortBean {

    private String firstLetter;

    private String letter;

    public SortBean(String firstLetter, String letter) {
        this.firstLetter = firstLetter;
        this.letter = letter;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return "SortBean{" +
                "firstLetter='" + firstLetter + '\'' +
                ", letter='" + letter + '\'' +
                '}';
    }
}
