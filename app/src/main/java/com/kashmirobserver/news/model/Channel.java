package com.kashmirobserver.news.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "channel", strict = false)
public class Channel {

    @ElementList(name = "item", inline = true)
    public ArrayList<Item> mItems;

    @Element(required = false)
    private String title;
    @Element(required = false)
    private String description;
    @Element(required = false)
    private String pubDate;
    @Element(required = false)
    private String lastBuildDate;
    @Element(required = false)
    private String language;



    public ArrayList<Item> getItems(){
        return mItems;
    }

    public void setItems(ArrayList<Item> items) {
        mItems = items;
    }
}
