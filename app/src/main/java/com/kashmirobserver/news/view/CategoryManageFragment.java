package com.kashmirobserver.news.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mehdi.kashmirobserver.R;
import com.kashmirobserver.news.controller.CategoryManagment;
import com.kashmirobserver.news.model.category;
import java.util.List;

public class CategoryManageFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterForCategory DisplayNews;
    public static List<category> cats;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_layout, null);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RefreshList();

        return rootView;
    }

    public void RefreshList() {
        cats = CategoryManagment.getManCategory(getContext());
        DisplayNews = new AdapterForCategory(getActivity(), cats, false);
        recyclerView.setAdapter(DisplayNews);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        if (menuVisible) {
            Log.i("TAG_Manage_FRAGMENT", "SHOW");
            if (recyclerView != null) {
                RefreshList();
            }
        } else {
            Log.i("TAG_Manage_FRAGMENT", "hide");
        }
    }
}
