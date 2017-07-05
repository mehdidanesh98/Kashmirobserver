package com.kashmirobserver.news.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.mehdi.kashmirobserver.R;
import com.google.gson.Gson;
import com.kashmirobserver.news.model.Categories;
import com.kashmirobserver.news.model.Channel;
import com.kashmirobserver.news.model.Constant;
import com.kashmirobserver.news.model.Item;
import com.kashmirobserver.news.model.News;
import com.kashmirobserver.news.model.category;

import java.util.ArrayList;

/**
 * Created by YOoHOo on 2017/06/17.
 */

public class CategoryManagment {

    public static ArrayList<category> getAddCategory(Context context) {
        ArrayList<category> cats = new ArrayList<category>();

        LocalStorge ls = new LocalStorge(context);
        String allCatStr = ls.getstr(Constant.LS_ADD_CAT);

        if (allCatStr.equalsIgnoreCase("")) {
            //Log.i("TAG_Manage_FRAGMENT","null");
            cats = getAllCat();
        } else {
            //Log.i("TAG_Manage_FRAGMENT",allCatStr);

            Gson gson = new Gson();
            Categories categories = gson.fromJson(allCatStr, Categories.class);
            cats = categories.categories;
        }
        return cats;
    }

    public static ArrayList<category> getManCategory(Context context) {
        LocalStorge ls = new LocalStorge(context);
        String manCatStr = ls.getstr(Constant.LS_MANAGE_CAT);
        if (!manCatStr.equalsIgnoreCase("")) {
            Gson gson = new Gson();
            Categories categories = gson.fromJson(manCatStr, Categories.class);
            return categories.categories;
        }
        return new ArrayList<category>();
    }

    public static ArrayList<category> getAllCat() {
        ArrayList<category> cat = new ArrayList<category>();

        cat.add(new category("In Depth", "", ""));
        cat.add(new category("In Depth - All", "In Depth", "in-depth"));
        cat.add(new category("KO Analysis", "In Depth", "in-depth/ko-analysis"));
        cat.add(new category("Opinions", "In Depth", "in-depth/opinions"));
        cat.add(new category("Interviews", "In Depth", "in-depth/interviews"));
        cat.add(new category("Letters", "In Depth", "in-depth/letters"));
        cat.add(new category("Heads & Tails", "In Depth", "in-depth/heads-and-tails"));
        cat.add(new category("Features ", "In Depth", "in-depth/features"));
        cat.add(new category("Editorial", "In Depth", "in-depth/editorial"));

        cat.add(new category("KO Highlights", "", ""));
        cat.add(new category("KO Highlights", "KO Highlights", "category/ko-highlights"));

        cat.add(new category("News", "", ""));
        cat.add(new category("News - All", "News", "news"));
        cat.add(new category("Local News", "News", "news/local-news"));
        cat.add(new category("City News", "News", "news/city-news"));
        cat.add(new category("Regional News", "News", "news/regional-news"));
        cat.add(new category("World News", "News", "news/world-news"));
        cat.add(new category("Kashmir News", "News", "category/kashmir"));
        cat.add(new category("Travel", "News", "category/travel"));
        cat.add(new category("Gadgets", "News", "category/gadgets"));

        cat.add(new category("Business", "", ""));
        cat.add(new category("Business", "Business", "news/business"));

        cat.add(new category("Technology", "", ""));
        cat.add(new category("Technology", "Technology", "news/technology"));

        cat.add(new category("Sports", "", ""));
        cat.add(new category("Sports", "Sports", "news/sports"));

        cat.add(new category("Health", "", ""));
        cat.add(new category("Health", "Health", "news/health"));
        return cat;
    }

    public static ArrayList<News> getNewsInChanel(Channel channel, Context context, String cat) {
        ArrayList<News> newses = new ArrayList<>();

        for (Item item : channel.mItems) {
            try {
                News news = new News();
                news.title = item.title;
                news.date = item.pubDate;
                news.author = item.author;
                news.pic = item.enclosure.url;
                news.img = R.drawable.pic1;
                news.text = item.description;
                int endtChar = item.source.url.lastIndexOf("/app");
                int startChar = item.source.url.lastIndexOf("/", endtChar - 1);
                news.cat = item.source.url.substring(startChar+1,endtChar).toUpperCase();
                newses.add(news);
            } catch (Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        return newses;
    }
}
