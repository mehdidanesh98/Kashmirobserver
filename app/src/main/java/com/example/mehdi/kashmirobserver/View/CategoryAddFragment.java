package com.example.mehdi.kashmirobserver.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mehdi.kashmirobserver.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAddFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterForCategory DisplayNews;
    private List<Model> allNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_layout, null);

        allNews = new ArrayList<>();
        DisplayNews = new AdapterForCategory(getActivity(), allNews);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        recyclerView.setAdapter(DisplayNews);
        prepareModels();

        return rootView;
    }


    private void prepareModels() {


        Model a = new Model("Sport");
        allNews.add(a);

        a = new Model("Politics");
        allNews.add(a);

        a = new Model("Science");
        allNews.add(a);

        a = new Model("Health");
        allNews.add(a);
        a = new Model("Iran");
        allNews.add(a);
        a = new Model("Health");
        allNews.add(a);
        a = new Model("Health");
        allNews.add(a);
        a = new Model("Health");
        allNews.add(a);

        DisplayNews.notifyDataSetChanged();
    }

}
