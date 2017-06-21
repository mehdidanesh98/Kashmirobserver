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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mehdi.kashmirobserver.R;
import com.kashmirobserver.news.controller.CategoryManagment;
import com.kashmirobserver.news.controller.NewsServices;
import com.kashmirobserver.news.controller.Tools;
import com.kashmirobserver.news.model.Channel;
import com.kashmirobserver.news.model.Item;
import com.kashmirobserver.news.model.News;
import com.kashmirobserver.news.model.RSS;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyNewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterForLive DisplayNews;
    private ProgressBar progressBar;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.live_layout, null);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_live);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        progressBar=(ProgressBar)rootView.findViewById(R.id.progressBar1);

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
            progressBar.setVisibility(View.VISIBLE);
            NewsServices newsServices = NewsServices.retrofit.create(NewsServices.class);
            Call<RSS> call = newsServices.repoContributors("in-depth","opinions");

            call.enqueue(new Callback<RSS>() {
                @Override
                public void onResponse(Call<RSS> call, Response<RSS> response) {
                    Log.i("onResponse",response.body().toString());
                    RSS rss=response.body();
                    Channel channel = rss.getChannel();
                    fillFirst(channel.mItems.remove(0));
                    Refreshlist(CategoryManagment.getNewsInChanel(channel,getContext()));
                    progressBar.setVisibility(View.GONE);
                }
                @Override
                public void onFailure(Call<RSS> call, Throwable t) {
                    Toast.makeText(getContext(),("Something went wrong: " + t.getMessage()),Toast.LENGTH_SHORT).show();
                    Log.e("onFailure","Something went wrong: " + t.getMessage());
                    progressBar.setVisibility(View.GONE);
                }
            });
        }else {
            //set Empty Rycyclerview
            Toast.makeText(getContext(),"Offline",Toast.LENGTH_SHORT).show();
        }
    }

    private void fillFirst(Item item){
        ImageView imageView=(ImageView) rootView.findViewById(R.id.thumbnail);
        TextView title=(TextView)rootView.findViewById(R.id.list_item_recyclerview_category_title);
        TextView time=(TextView)rootView.findViewById(R.id.timecard);
        TextView cat=(TextView)rootView.findViewById(R.id.categorycard);

        Glide.with(getContext()).load(item.enclosure.url).error(R.drawable.remove_category).into(imageView);
        title.setText(item.title);
        time.setText(item.pubDate);
        cat.setText("in depth");
    }

}
