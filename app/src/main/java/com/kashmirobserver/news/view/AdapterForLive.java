package com.kashmirobserver.news.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mehdi.kashmirobserver.R;
import com.google.gson.Gson;
import com.kashmirobserver.news.controller.AnimationUtils;
import com.kashmirobserver.news.model.Constant;
import com.kashmirobserver.news.model.News;

import java.util.List;

public class AdapterForLive extends RecyclerView.Adapter<AdapterForLive.MyViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<News> allNews;
    private News news;
    private int prePosition = 0;

    public AdapterForLive(Context mContext, List<News> allNews) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.allNews = allNews;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_item_recyclerview_live, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        news = allNews.get(position);
        holder.title.setText(news.gettitle());
        holder.date.setText(news.date);
        holder.category.setText("In Depth");

        // loading album cover using Glide library
        Glide.with(mContext).load(news.pic).into(holder.thumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                Intent intent = new Intent(mContext, DetailNews.class);
                intent.putExtra(Constant.DETAIL_NEWS, gson.toJson(news));

                mContext.startActivity(intent);
            }
        });


        if (position > prePosition) {
            new AnimationUtils().animate(holder, true);
        } else {
            new AnimationUtils().animate(holder, false);
        }

        prePosition = position;

    }

    @Override
    public int getItemCount() {
        return allNews.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date, category;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.list_item_recyclerview_category_title);
            date = (TextView) view.findViewById(R.id.time);
            category = (TextView) view.findViewById(R.id.category);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

        }
    }
}
