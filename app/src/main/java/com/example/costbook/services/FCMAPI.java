package com.example.costbook.services;

import com.example.costbook.retrofit.CostPost;
import com.example.costbook.Utils.FCM;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FCMAPI {
    public CostPost getRetroClient() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(FCM.API_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory((GsonConverterFactory
                        .create()));
        Retrofit retrofit = builder.build();
        return retrofit.create(CostPost.class);
    }
}
