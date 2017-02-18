package com.kashmirobserver.news.view;

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

public class FeatureFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterForLive DisplayNews;
    private List<Model> allNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.live_layout, null);

        allNews = new ArrayList<>();
        DisplayNews = new AdapterForLive(getActivity(), allNews);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_live);
        int mNoOfColumns = Utility.calculateNoOfColumns(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        recyclerView.setAdapter(DisplayNews);
        recyclerView.setFocusable(false);
        prepareModels();

        return rootView;
    }

    public static class Utility {

        public static int calculateNoOfColumns(Context context) {

            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            int noOfColumns = (int) (dpWidth / 180);
            return noOfColumns;

        }
    }

    private void prepareModels() {
        int[] covers = new int[]{
                R.drawable.pic1,
                R.drawable.pic2,
                R.drawable.pic3,
                R.drawable.pic4,
                R.drawable.pic5,
                R.drawable.pic6,
                R.drawable.pic7,
        };

        Model a = new Model("True Romance", covers[0]);
        allNews.add(a);

        a = new Model("Xscpae", covers[1]);
        allNews.add(a);

        a = new Model("Maroon 5", covers[2]);
        allNews.add(a);

        a = new Model("Born to Die", covers[3]);
        allNews.add(a);

        a = new Model("Honeymoon", covers[4]);
        allNews.add(a);

        a = new Model("I Need a Doctor", covers[5]);
        allNews.add(a);

        a = new Model("Loud", covers[6]);
        allNews.add(a);

        DisplayNews.notifyDataSetChanged();
    }

}
