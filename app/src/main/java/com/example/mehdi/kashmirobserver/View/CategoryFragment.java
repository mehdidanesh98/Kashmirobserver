package com.example.mehdi.kashmirobserver.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mehdi.kashmirobserver.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterForCategory DisplayNews;
    private List<Model> CatNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_layout, null);

        CatNews = new ArrayList<>();
        DisplayNews = new AdapterForCategory(getActivity(), CatNews);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        recyclerView.setAdapter(DisplayNews);
        prepareModels();

        return rootView;
    }


    private void prepareModels() {
        int[] covers = new int[]{
                R.drawable.sport1,
                R.drawable.politic,
                R.drawable.sience,
                R.drawable.health,
        };
        Model a = new Model("Sport", covers[0]);
        CatNews.add(a);

        a = new Model("Politics", covers[1]);
        CatNews.add(a);

        a = new Model("Science", covers[2]);
        CatNews.add(a);

        a = new Model("Health", covers[3]);
        CatNews.add(a);

        DisplayNews.notifyDataSetChanged();
    }
}
