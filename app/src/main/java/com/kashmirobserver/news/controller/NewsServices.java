package com.kashmirobserver.news.controller;

import com.kashmirobserver.news.model.RSS;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by YOoHOo on 2017/06/13.
 */

public interface NewsServices {
    @GET("{cat}/app")
    Call<RSS> repoContributors(
            @Path("cat") String cat);


//    public static final Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("https://kashmirobserver.net/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://kashmirobserver.net/")
            .client(new OkHttpClient())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build();
}
