package com.kashmirobserver.news.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mehdi.kashmirobserver.R;
import com.google.gson.Gson;
import com.kashmirobserver.news.controller.AnimationUtils;
import com.kashmirobserver.news.controller.LocalStorge;
import com.kashmirobserver.news.model.Categories;
import com.kashmirobserver.news.model.Constant;
import com.kashmirobserver.news.model.category;

import java.util.ArrayList;
import java.util.List;

public class AdapterForCategoryMan extends RecyclerView.Adapter<AdapterForCategoryMan.MyViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<category> allcat;
    private int prePosition = 0;

    public AdapterForCategoryMan(Context mContext, ArrayList<category> cats) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.allcat = cats;
    }

    @Override
    public AdapterForCategoryMan.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_item_recyclerview_category, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.title.setText(allcat.get(position).getName());
        holder.img.setImageResource(R.drawable.remove_category);
        holder.img.setVisibility(View.VISIBLE);
        holder.title.setTextColor(Color.parseColor("#212121"));
        holder.title.setTextSize(13);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, allcat.get(position).getName() + " removed of My news", Toast.LENGTH_SHORT).show();
                removeAt(position);
            }
        });

        if (position > prePosition) {
            new AnimationUtils().animate(holder, true);
        } else {
            new AnimationUtils().animate(holder, false);
        }

        prePosition = position;

    }

    public void removeAt(int position) {
        category AddCat = allcat.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, allcat.size());

        Gson gson = new Gson();
        Categories categories = new Categories();
        categories.categories =  allcat;

        LocalStorge localStorge = new LocalStorge(mContext);
        localStorge.Setstr(Constant.LS_MANAGE_CAT, gson.toJson(categories));

        categories = gson.fromJson(localStorge.getstr(Constant.LS_ADD_CAT), Categories.class);
        for (int i = 0; i < categories.categories.size(); i++) {
            if (categories.categories.get(i).getName().equalsIgnoreCase(AddCat.getParent())) {
                categories.categories.add((i + 1), AddCat);
                break;
            }
        }
        localStorge.Setstr(Constant.LS_ADD_CAT, gson.toJson(categories));
    }

    @Override
    public int getItemCount() {
        return allcat.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.list_item_recyclerview_category_title);
            img = (ImageView) view.findViewById(R.id.list_item_recyclerview_category_thumbnail);
        }
    }
}
