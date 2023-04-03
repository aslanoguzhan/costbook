package com.example.costbook.ui.unitcost_infopop;

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

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Unitcost extends Activity {
    private Button close;
    private TextView unitcost;
    private int productId , gardenId ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.unitcost );
        ButterKnife.bind( this );

        gardenId=getIntent().getIntExtra("gardenID",0);
        productId=getIntent().getIntExtra("productID",0);
        getUnitCostmethod(productId,gardenId);

        close=findViewById(R.id.PopupInfoClose);
        unitcost=findViewById(R.id.unitcost);

       close.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
    }




    private void getUnitCostmethod(int productID, int gardenID) {

        RestInterface apiService = API.getClient().create(RestInterface.class);
        LoadingProgress loadingProgress = new LoadingProgress(this);
        loadingProgress.startLoading();
        Call<Double> call = apiService.getUnitCost(productID,gardenID);
        call.enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                String a=null;
                if(User_Login.userID>0) {
                if(response.isSuccessful()) {

                 a=response.body().toString();
                 unitcost.setText( a+ "TL");

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
                    Toast.makeText(getApplicationContext(), "Bu tarla ve ürüne ait kilo maliyeti bilgisi bulunamadı.", Toast.LENGTH_LONG).show();
                    loadingProgress.dismissProgress();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "hata oluştu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
