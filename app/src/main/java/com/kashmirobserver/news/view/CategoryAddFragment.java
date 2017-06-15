package com.kashmirobserver.news.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mehdi.kashmirobserver.R;
import com.google.gson.Gson;
import com.kashmirobserver.news.controller.LocalStorge;
import com.kashmirobserver.news.model.Categories;
import com.kashmirobserver.news.model.Constant;
import com.kashmirobserver.news.model.category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAddFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterForCategory DisplayNews;
    private List<category> allNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_layout, null);

        allNews = getAddCategory();

        DisplayNews = new AdapterForCategory(getActivity(), allNews);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        recyclerView.setAdapter(DisplayNews);

        return rootView;
    }

    public List<category> getAddCategory() {
        ArrayList<category> cats = new ArrayList<category>();

        LocalStorge ls = new LocalStorge(getContext());
        String allCatStr = ls.getstr(Constant.LS_ADD_CAT);

        if (allCatStr.equalsIgnoreCase(""))
        {
            Toast.makeText(getContext(),allCatStr,Toast.LENGTH_LONG);
            cats = new category().getAllCat();
        }else {
            Toast.makeText(getContext(),allCatStr,Toast.LENGTH_LONG);

            Gson gson=new Gson();
            Categories categories = gson.fromJson(allCatStr, Categories.class);
            cats = categories.categories;
        }
        return cats;
    }
}
