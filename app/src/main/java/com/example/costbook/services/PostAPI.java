package com.example.costbook.services;

import com.example.costbook.Utils.Utils;
import com.example.costbook.retrofit.CostPost;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PostAPI {
    public CostPost getRetroClient() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Utils.API_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory((GsonConverterFactory
                        .create()));
        Retrofit retrofit = builder.build();
        return retrofit.create(CostPost.class);
    }
}
