package com.mht.stetho.network;


import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpWithStetho())
                    .baseUrl(BASE_URL)
                    .build();
        }

        return retrofit;
    }

    private static OkHttpClient getOkHttpWithStetho() {

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }
}
