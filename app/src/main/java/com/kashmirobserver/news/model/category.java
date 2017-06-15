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

    public ArrayList<category> getAllCat(){
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
