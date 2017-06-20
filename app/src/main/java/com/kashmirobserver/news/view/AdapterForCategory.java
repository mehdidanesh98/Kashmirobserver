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

public class AdapterForCategory extends RecyclerView.Adapter<AdapterForCategory.MyViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<category> allcat;
    private int prePosition = 0;
    private boolean mode;

    public AdapterForCategory(Context mContext, List<category> cats, boolean mode) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.allcat = cats;
        this.mode = mode;
    }

    @Override
    public AdapterForCategory.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_item_recyclerview_category, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.title.setText(allcat.get(position).getName());
        if (mode)
            holder.img.setImageResource(R.drawable.add_category);
        else
            holder.img.setImageResource(R.drawable.remove_category);

        if (allcat.get(position).getParent().equalsIgnoreCase("")) {
            holder.img.setVisibility(View.GONE);
            holder.itemView.setEnabled(false);
            holder.title.setTextSize(18);
            holder.title.setTextColor(Color.parseColor("#000000"));
            holder.title.setHeight(120);
        } else {
            holder.img.setVisibility(View.VISIBLE);
            holder.title.setTextColor(Color.parseColor("#212121"));
            holder.title.setTextSize(13);
            if(mode) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, allcat.get(position).getName() + " added to My news", Toast.LENGTH_SHORT).show();
                        removeAt(position);
                    }
                });
            }
        }

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
        categories.categories = (ArrayList<category>) allcat;

        LocalStorge localStorge = new LocalStorge(mContext);
        localStorge.Setstr(Constant.LS_ADD_CAT, gson.toJson(categories));

        categories = gson.fromJson(localStorge.getstr(Constant.LS_MANAGE_CAT), Categories.class);
        if (categories == null)
            categories = new Categories();
        categories.categories.add(AddCat);
        localStorge.Setstr(Constant.LS_MANAGE_CAT, gson.toJson(categories));
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
