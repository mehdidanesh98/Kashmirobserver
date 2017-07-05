package com.kashmirobserver.news.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.kashmirobserver.news.model.category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyNewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterForLive DisplayNews = null;
    private CardView card_view;
    private ProgressBar progressBar;
    private View rootView;
    private LinearLayout overview, settingNetwork;
    private TextView txtSettingNetwork;
    private Button againButton;
    private SwipeRefreshLayout refreshLayout;
    private int reqLen, sucLen = 0;
    private String errTxt = "", cat = "";
    private ArrayList<News> allNews = new ArrayList<News>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.live_layout, null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_live);
        recyclerView.setLayoutManager(linearLayoutManager);

        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        card_view = (CardView) rootView.findViewById(R.id.card_view1);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar1);
        overview = (LinearLayout) rootView.findViewById(R.id.overview);
        settingNetwork = (LinearLayout) rootView.findViewById(R.id.layout_setting_network);
        againButton = (Button) rootView.findViewById(R.id.btn_try_again);
        txtSettingNetwork = (TextView) rootView.findViewById(R.id.btn_setting_network);
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

    public void getNews() {
        //loading
        if (Tools.isOnline(getContext())) {
            RefreshList();
        } else {
            settingNetwork.setVisibility(View.VISIBLE);
            txtSettingNetwork.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                }
            });
            againButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Tools.isOnline(getActivity())) {
                        settingNetwork.setVisibility(View.GONE);
                        RefreshList();
                    } else
                        Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void RefreshList() {
        reqLen = 0; sucLen = 0; errTxt = ""; allNews = new ArrayList<News>();

        progressBar.setVisibility(View.VISIBLE);
        NewsServices newsServices = NewsServices.retrofit.create(NewsServices.class);
        final ArrayList<category> categories = CategoryManagment.getManCategory(getActivity());

        if (categories.size() > 0) {
            for (int i = 0; i < categories.size(); i++) {
                Call<RSS> call = newsServices.repoContributors(categories.get(i).getUrl());
                cat = categories.get(i).getName();

                call.enqueue(new Callback<RSS>() {
                    @Override
                    public void onResponse(Call<RSS> call, Response<RSS> response) {
                        reqLen++;

                        try {
                            RSS rss = response.body();
                            Channel channel = rss.getChannel();
                            allNews.addAll(CategoryManagment.getNewsInChanel(channel, getContext(), cat));
                            sucLen++;

                            if (sucLen == 1) {
                                fillFirst(channel.mItems.get(0));
                                DisplayNews = new AdapterForLive(getActivity(), allNews);
                                recyclerView.setAdapter(DisplayNews);
                                card_view.setVisibility(View.VISIBLE);
                            } else {
                                if (DisplayNews == null)
                                    Thread.sleep(2000);
                                if (DisplayNews == null)
                                    Thread.sleep(4000);
                                DisplayNews.notifyDataSetChanged();
                            }

                        } catch (Exception e) {
                            errTxt += e.getMessage() + "\n";
                        } finally {
                            if (reqLen == categories.size())
                                finishReq();
                        }

                    }

                    @Override
                    public void onFailure(Call<RSS> call, Throwable t) {
                        errTxt += t.getMessage() + "\n";
                        reqLen++;

                        if (reqLen == categories.size())
                            finishReq();
                    }
                });
            }
        } else {
            Toast.makeText(getActivity(), "You have to select a category item in category tab", Toast.LENGTH_LONG).show();
            overview.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    private void fillFirst(Item item) {
        ImageView imageView = (ImageView) rootView.findViewById(R.id.thumbnail);
        TextView title = (TextView) rootView.findViewById(R.id.list_item_recyclerview_category_title);
        TextView time = (TextView) rootView.findViewById(R.id.timecard);
        TextView cat = (TextView) rootView.findViewById(R.id.categorycard);

        Glide.with(getContext()).load(item.enclosure.url).error(R.drawable.remove_category).into(imageView);
        title.setText(item.title);
        time.setText(item.pubDate);
        int endtChar = item.source.url.lastIndexOf("/app");
        int startChar = item.source.url.lastIndexOf("/", endtChar - 1);
        cat.setText(item.source.url.substring(startChar+1,endtChar).toUpperCase());
    }

    private void finishReq() {
        progressBar.setVisibility(View.GONE);
        if (!errTxt.equalsIgnoreCase("")) {
            Toast.makeText(getContext(), ("Something went wrong: " + errTxt), Toast.LENGTH_LONG).show();
            Log.e("onFailure", "Something went wrong: " + errTxt);
        } else {
            Toast.makeText(getContext(), "All Categories Loaded", Toast.LENGTH_SHORT).show();
        }
    }

}
