package com.kashmirobserver.news.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mehdi.kashmirobserver.R;
import com.kashmirobserver.news.controller.CategoryManagment;
import com.kashmirobserver.news.controller.NewsServices;
import com.kashmirobserver.news.controller.Tools;
import com.kashmirobserver.news.model.Channel;
import com.kashmirobserver.news.model.News;
import com.kashmirobserver.news.model.RSS;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyNewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterForLive DisplayNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.live_layout, null);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_live);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getNews();

        return rootView;
    }

    public void Refreshlist(ArrayList<News> newes){
        DisplayNews = new AdapterForLive(getActivity(),newes);
        recyclerView.setAdapter(DisplayNews);
    }

    public static class Utility {

        public static int calculateNoOfColumns(Context context) {

            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            int noOfColumns = (int) (dpWidth / 180);
            return noOfColumns;

        }
    }

    public void getNews(){
        //loading
        if (Tools.isOnline(getContext())){

            NewsServices newsServices = NewsServices.retrofit.create(NewsServices.class);
            Call<RSS> call = newsServices.repoContributors("in-depth","opinions");

            call.enqueue(new Callback<RSS>() {
                @Override
                public void onResponse(Call<RSS> call, Response<RSS> response) {
                    Log.i("onResponse",response.body().toString());
                    RSS rss=response.body();
                    Channel channel = rss.getChannel();
                    Refreshlist(CategoryManagment.getNewsInChanel(channel));
                }
                @Override
                public void onFailure(Call<RSS> call, Throwable t) {
                    Toast.makeText(getContext(),("Something went wrong: " + t.getMessage()),Toast.LENGTH_SHORT).show();
                    Log.e("onFailure","Something went wrong: " + t.getMessage());
                }
            });
        }else {
            //set Empty Rycyclerview
            Toast.makeText(getContext(),"Offline",Toast.LENGTH_SHORT).show();
        }
    }

}
