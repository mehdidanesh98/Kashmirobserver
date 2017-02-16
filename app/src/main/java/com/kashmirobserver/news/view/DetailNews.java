package com.kashmirobserver.news.view;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.mehdi.kashmirobserver.R;

public class DetailNews extends AppCompatActivity {

    private CollapsingToolbarLayout banner;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = (ImageView) findViewById(R.id.imgbanner);
        banner = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        banner.setContentScrimColor(getResources().getColor(R.color.colorBackTab));
        Bundle bundle = getIntent().getExtras();
        String Title = bundle.getString("nameNews");
        int pos = bundle.getInt("pos",1);
        String pic="pic"+pos;

        int i=getApplicationContext().getResources().getIdentifier("drawable/"+pic, null, getApplicationContext().getPackageName());

        banner.setTitle(Title);
        imageView.setImageResource(i);


    }

    public void moreMenu(View v) {
        final PopupMenu popup = new PopupMenu(DetailNews.this, v);
        final MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.show();
    }
}
