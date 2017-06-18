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
import com.kashmirobserver.news.controller.NewsServices;
import com.kashmirobserver.news.controller.Tools;
import com.kashmirobserver.news.model.Channel;
import com.kashmirobserver.news.model.News;
import com.kashmirobserver.news.model.RSS;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyNewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterForLive DisplayNews;
    private List<News> allNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.live_layout, null);

        allNews = new ArrayList<>();
        prepareModels();
        DisplayNews = new AdapterForLive(getActivity(), allNews);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_live);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(DisplayNews);
        getNews();
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

        News a = new News("True Romance", covers[0]);
        allNews.add(a);

        a = new News("Xscpae", covers[1]);
        allNews.add(a);

        a = new News("Maroon 5", covers[2]);
        allNews.add(a);

        a = new News("Born to Die", covers[3]);
        allNews.add(a);

        a = new News("Honeymoon", covers[4]);
        allNews.add(a);

        a = new News("I Need a Doctor", covers[5]);
        allNews.add(a);

        a = new News("Loud", covers[6]);
        allNews.add(a);

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
                    Toast.makeText(getContext(),"real don",Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Call<RSS> call, Throwable t) {

                    Log.e("onFailure","Something went wrong: " + t.getMessage());
                }
            });
            Toast.makeText(getContext(),"don",Toast.LENGTH_SHORT).show();
        }else {
            //set Empty Rycyclerview
            Toast.makeText(getContext(),"Offline",Toast.LENGTH_SHORT).show();
        }
    }

}
