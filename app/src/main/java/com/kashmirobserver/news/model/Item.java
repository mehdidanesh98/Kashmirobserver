package com.kashmirobserver.news.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "item", strict = false)
public class Item implements Parcelable {

    @Element(name = "author")
    public String author;

    @Element(name = "pubDate")
    public String pubDate;

    @Element(name = "title")
    public String title;

    @Element(name = "link")
    public String link;

    @Element(name = "description")
    public String description;

    @Element(name = "enclosure")
    public Enclosure enclosure;

    @Element(name = "source")
    public Source source;

    public String src;


    public Item(@Element(name = "title") String title,
                @Element(name = "description") String description,
                @Element(name = "pubDate") String pubDate,
                @Element(name = "author") String author) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.author = author;
    }

    public Item(Parcel in) {
        title = in.readString();
        description = in.readString();
        pubDate = in.readString();
        author = in.readString();
        src = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };


    public String getPubDate() {
        return pubDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(pubDate);
        dest.writeString(author);
        dest.writeString(src);
    }
}
