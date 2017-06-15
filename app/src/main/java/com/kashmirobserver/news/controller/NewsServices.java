package com.kashmirobserver.news.controller;

import com.kashmirobserver.news.model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by YOoHOo on 2017/06/13.
 */

public interface NewsServices {
    @GET("{cat}/{owner}/app")
    Call<List<News>> repoContributors(
            @Path("cat") String cat,
            @Path("owner") String owner);


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://kashmirobserver.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
