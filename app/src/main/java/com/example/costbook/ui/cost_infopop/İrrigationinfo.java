package com.example.costbook.ui.cost_infopop;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.costbook.R;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.services.API;
import com.example.costbook.ui.LoadingProgress;
import com.example.costbook.ui.login.User_Login;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class İrrigationinfo extends  Activity {

    private Button close;
    private Spinner getGarden, getProduct;
    private int productıd,gardenıd;
    private TextView cost;
    private LoadingProgress loadingProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.irrigationinfo);
        close = findViewById(R.id.PopupInfoClose);
        cost=findViewById(R.id.costirrigation);
        ButterKnife.bind(this);
        gardenıd=getIntent().getIntExtra("gardenID",0);
        productıd=getIntent().getIntExtra("productID",0);

        getİrrigationcostmethod(productıd,gardenıd);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }




    private void getİrrigationcostmethod(int productID, int gardenID) {

        RestInterface apiService = API.getClient().create(RestInterface.class);
        LoadingProgress loadingProgress = new LoadingProgress(this);
        loadingProgress.startLoading();
        Call<Object> call = apiService.getİrrigationCost(productID,gardenID);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                String a=null ;
                if(User_Login.userID>0){
                if(response.isSuccessful()) {
                    a = ((LinkedTreeMap) ((ArrayList) response.body()).get(0)).values().toString();
                    a = a.substring(a.indexOf("[") + 1, a.indexOf("]"));
                    cost.setText(a + "TL");

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingProgress.dismissProgress();
                        }
                    }, 200);
                }
                }
                if( a==null){
                    Toast.makeText(getApplicationContext(), "Bu kategoriye ait masraf bulunamadı.", Toast.LENGTH_LONG).show();
                    loadingProgress.dismissProgress();
                    finish();
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

}
