package com.mht.stetho.network;


import retrofit2.Retrofit;

public class NetworkManager {

    private Retrofit getRetrofit() {
        return RetrofitClient.getClient();
    }

    public NetworkApi getNetworkApi() {
        return getRetrofit().create(NetworkApi.class);
    }

}
