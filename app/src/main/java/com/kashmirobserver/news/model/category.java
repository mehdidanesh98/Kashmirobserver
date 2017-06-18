package com.kashmirobserver.news.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lincoln on 18/05/16.
 */
public class category {
    private String name;
    private String parent;
    private String url;

    public category() {
    }

    public category(String name,String parent,String url) {
        this.name = name;
        this.parent = parent;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
