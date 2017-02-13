package com.example.mehdi.kashmirobserver.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mehdi.kashmirobserver.R;

import java.util.List;

public class AdapterForCategory extends RecyclerView.Adapter<AdapterForCategory.MyViewHolder> {

    private Context mContext;
    private List<Model> allNews;
    private Model news;
    public AdapterForCategory(Context mContext, List<Model> allNews)
    {
        this.mContext = mContext;
        this.allNews = allNews;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail_category);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_recyclerview_category, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        news = allNews.get(position);
        holder.title.setText(news.getName());
        Glide.with(mContext).load(news.getThumbnail()).into(holder.thumbnail);

    }


    @Override
    public int getItemCount() {
        return allNews.size();
    }
}
