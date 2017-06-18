package com.kashmirobserver.news.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.kashmirobserver.news.model.Categories;
import com.kashmirobserver.news.model.Constant;
import com.kashmirobserver.news.model.category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YOoHOo on 2017/06/17.
 */

public class CategoryManagment {

    public static List<category> getAddCategory(Context context) {
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

    public static List<category> getManCategory(Context context) {
        LocalStorge ls = new LocalStorge(context);
        String manCatStr = ls.getstr(Constant.LS_MANAGE_CAT);
        if (!manCatStr.equalsIgnoreCase("")) {
            Gson gson = new Gson();
            Categories categories = gson.fromJson(manCatStr, Categories.class);
            return categories.categories;
        }
        return new ArrayList<category>();
    }

    public static ArrayList<category> getAllCat(){
        ArrayList<category> cat =new ArrayList<category>();

        cat.add(new category("In Depth","",""));
        cat.add(new category("All","In Depth","in-depth"));
        cat.add(new category("KO Analysis","In Depth","in-depth/ko-analysis"));
        cat.add(new category("Opinions","In Depth","in-depth/opinions"));
        cat.add(new category("Interviews","In Depth","in-depth/interviews"));
        cat.add(new category("Letters","In Depth","in-depth/letters"));
        cat.add(new category("Heads & Tails","In Depth","in-depth/heads-and-tails"));
        cat.add(new category("Features ","In Depth","in-depth/features"));
        cat.add(new category("Editorial","In Depth","in-depth/editorial"));

        cat.add(new category("KO Highlights","",""));
        cat.add(new category("KO Highlights","KO Highlights","category/ko-highlights"));

        cat.add(new category("News","",""));
        cat.add(new category("All","News","news"));
        cat.add(new category("Local News","News","news/local-news"));
        cat.add(new category("City News","News","news/city-news"));
        cat.add(new category("Regional News","News","news/regional-news"));
        cat.add(new category("World News","News","news/world-news"));
        cat.add(new category("Kashmir News","News","category/kashmir"));
        cat.add(new category("Travel","News","category/travel"));
        cat.add(new category("Gadgets","News","category/gadgets"));

        cat.add(new category("Business","",""));
        cat.add(new category("Business","Business","news/business"));

        cat.add(new category("Technology","",""));
        cat.add(new category("Technology","Business","news/technology"));

        cat.add(new category("Sports","",""));
        cat.add(new category("Sports","Sports","news/sports"));

        cat.add(new category("Health","",""));
        cat.add(new category("Health","Health","news/health"));
        return cat;
    }
}
