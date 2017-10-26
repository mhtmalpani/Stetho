package com.mht.stetho.network;


import com.mht.stetho.model.MyPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkApi {

    @GET("posts/{id}")
    Call<MyPojo> getData(@Path("id") int id);
}
