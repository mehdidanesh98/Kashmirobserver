package com.kashmirobserver.news.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mehdi.kashmirobserver.R;
import com.google.gson.Gson;
import com.kashmirobserver.news.controller.LocalStorge;
import com.kashmirobserver.news.model.Categories;
import com.kashmirobserver.news.model.Constant;
import com.kashmirobserver.news.model.category;

import java.util.ArrayList;
import java.util.List;

public class CategoryManageFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterForCategory DisplayNews;
    private List<category> cats;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_layout, null);

        cats = getManCategory();
        DisplayNews = new AdapterForCategory(getActivity(), cats);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        recyclerView.setAdapter(DisplayNews);

        return rootView;
    }
    public List<category> getManCategory() {
        LocalStorge ls = new LocalStorge(getContext());
        String manCatStr = ls.getstr(Constant.LS_MANAGE_CAT);
        if (!manCatStr.equalsIgnoreCase("")) {
            Gson gson = new Gson();
            Categories categories = gson.fromJson(manCatStr, Categories.class);
            return categories.categories;
        }
        return new ArrayList<category>();
    }

}
