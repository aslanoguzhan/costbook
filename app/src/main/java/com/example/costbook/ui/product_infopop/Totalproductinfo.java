package com.example.costbook.ui.product_infopop;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
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

public class Totalproductinfo extends Activity  {
    private Button close;
    private TextView totalproduct;
    private  int productId , gardenId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totalproduct_info);
        ButterKnife.bind(this);
        gardenId=getIntent().getIntExtra("gardenID",0);
        productId=getIntent().getIntExtra("productID",0);
        getTotalproductmethod(productId,gardenId);

        close = findViewById(R.id.PopupInfoClose);
        totalproduct = findViewById(R.id.producttotal);



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    private void getTotalproductmethod(int productID, int gardenID) {


        RestInterface apiService = API.getClient().create(RestInterface.class);
        LoadingProgress loadingProgress = new LoadingProgress(this);
        loadingProgress.startLoading();
        Call<Object> call = apiService.getCategoryProduct(productID,gardenID);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                 String a=null;
                if(User_Login.userID>0) {
                    if (response.isSuccessful()) {
                        a = ((LinkedTreeMap) ((ArrayList) response.body()).get(0)).values().toString();
                        a = a.substring(a.indexOf("[") + 1, a.indexOf("]"));
                        totalproduct.setText(a + "KG");

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
                    Toast.makeText(getApplicationContext(), "Bu tarla ve ürüne ait üretim miktarı bilgisi bulunamadı.", Toast.LENGTH_LONG).show();
                    loadingProgress.dismissProgress();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "hata oluştu", Toast.LENGTH_SHORT).show();
            }


        });
    }
}
