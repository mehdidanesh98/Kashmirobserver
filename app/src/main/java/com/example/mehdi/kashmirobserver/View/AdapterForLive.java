package com.example.mehdi.kashmirobserver.View;

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

import java.util.List;

public class AdapterForLive extends RecyclerView.Adapter<AdapterForLive.MyViewHolder> {

    private Context mContext;
    private List<Model> allNews;
    private Model news;

    public AdapterForLive(Context mContext, List<Model> allNews) {
        this.mContext = mContext;
        this.allNews = allNews;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_recyclerview_live, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        news = allNews.get(position);
        holder.title.setText(news.getName());

        // loading album cover using Glide library
        Glide.with(mContext).load(news.getThumbnail()).into(holder.thumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailNews.class);
                intent.putExtra("nameNews", allNews.get(position).getName());
                intent.putExtra("pos", position+1);

                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return allNews.size();
    }
}
