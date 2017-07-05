package com.kashmirobserver.news.view;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mehdi.kashmirobserver.R;
import com.google.gson.Gson;
import com.kashmirobserver.news.model.Constant;
import com.kashmirobserver.news.model.News;

public class DetailNews extends AppCompatActivity {

    private CollapsingToolbarLayout banner;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = (ImageView) findViewById(R.id.imgbanner);
        textView = (TextView) findViewById(R.id.detail_text);

        banner = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        banner.setContentScrimColor(getResources().getColor(R.color.colorBackTab));
        banner.setExpandedTitleColor(getResources().getColor(R.color.cardview_light_background));
        banner.setCollapsedTitleTextColor(getResources().getColor(R.color.cardview_light_background));

        set_textview_style();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String value = bundle.getString(Constant.DETAIL_NEWS);

            Gson gson = new Gson();
            News news = gson.fromJson(value, News.class);

            banner.setTitle(news.title);
            Glide.with(getApplicationContext()).load(news.pic).into(imageView);
            //Log.d("Test Text", news.text);
            textView.setText(Html.fromHtml(news.text));
        }
    }

    private void set_textview_style() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String font_face = sp.getString("font_face", "");
        int font_size = Integer.parseInt(sp.getString("font_size", "16"));

        if (!font_face.equalsIgnoreCase("") && !font_face.equalsIgnoreCase("16")) {
            Toast.makeText(this, font_face, Toast.LENGTH_LONG).show();
            Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/" + font_face);

            textView.setTypeface(font);
            textView.setTextSize(font_size);
        }
    }

    public void moreMenu(View v) {
        final PopupMenu popup = new PopupMenu(DetailNews.this, v);
        final MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.show();
    }

    public void back(View v) {
        finish();
    }
}
