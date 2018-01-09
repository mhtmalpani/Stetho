package com.mht.stetho;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mht.stetho.model.MyPojo;
import com.mht.stetho.network.NetworkManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private NetworkManager networkManager;

    private Button networkCall;
    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkCall = findViewById(R.id.networkCall);
        responseText = findViewById(R.id.response);

        networkManager = new NetworkManager();


        networkCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                responseText.setText("Fetching data...");

                networkManager.getNetworkApi().getData(12).enqueue(new Callback<MyPojo>() {

                    @Override
                    public void onResponse(Call<MyPojo> call, Response<MyPojo> response) {
                        responseText.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<MyPojo> call, Throwable t) {
                        responseText.setText(t.getMessage());
                    }
                });
            }
        });
    }
}
