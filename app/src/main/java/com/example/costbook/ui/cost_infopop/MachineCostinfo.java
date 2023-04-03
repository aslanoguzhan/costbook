package com.example.costbook.ui.cost_infopop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.costbook.R;
import com.example.costbook.pojo.Costs;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.services.API;
import com.example.costbook.ui.LoadingProgress;
import com.example.costbook.ui.login.User_Login;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MachineCostinfo extends Activity  {
    private Button close;
    private Intent intent = new Intent();
    private int productID , gardenID ;
    private TextView category;
    private List<Costs> costsList = new ArrayList<>();
    private ImageView fuelcost;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.costcategory_info);
        ButterKnife.bind(this);

        productID=getIntent().getIntExtra("gardenID",0);
        gardenID=getIntent().getIntExtra("productID",0);
        close = findViewById(R.id.PopupInfoClose);
        category = findViewById(R.id.costmachine);
        postMachinecostmethod(productID, gardenID);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }






    private void postMachinecostmethod(int productID, int gardenID) {

        RestInterface apiService = API.getClient().create(RestInterface.class);
        LoadingProgress loadingProgress = new LoadingProgress(this);
        loadingProgress.startLoading();
        Call<Object> call = apiService.getMachineCost(productID, gardenID);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                String a=null ;
                if(User_Login.userID>0) {
                    if (response.isSuccessful()) {
                        a = ((LinkedTreeMap) ((ArrayList) response.body()).get(0)).values().toString();
                        a = a.substring(a.indexOf("[") + 1, a.indexOf("]"));
                        category.setText(a + "TL");

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
                Log.i("error", "failure");
            }


        });
    }
}