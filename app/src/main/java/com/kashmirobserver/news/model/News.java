package com.kashmirobserver.news.model;

/**
 * Created by YOoHOo on 2017/02/16.
 */

public class News {
    public String title;
    public String text;
    public int img;
    public String date;
    public String pic;
    public  String author;
    public String cat;
    public String link;

    public News(String title,int img){
        this.title=title;
        this.img=img;
    }

    public News(){
    }

    public String getdate() {
        return date;
    }

    public void setdate(String value) {
        this.date = value;
    }
    public int getimg() {
        return img;
    }

    public void setimg(int value) {
        this.img = value;
    }
    public String gettext() {
        return text;
    }

    public void settext(String value) {
        this.text = value;
    }
    public String gettitle() {
        return title;
    }

    public void settitle(String value) {
        this.title = value;
    }
}
