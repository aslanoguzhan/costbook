package com.example.costbook.ui.cost_infopop;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.costbook.R;
import com.example.costbook.adapter.MountCostRecyclerAdapter;
import com.example.costbook.pojo.MounthCosts;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.services.API;
import com.example.costbook.ui.Cost;
import com.example.costbook.ui.LoadingProgress;
import com.example.costbook.ui.login.User_Login;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MounthCost extends Activity {
    private int productıd, gardenıd, userıd;
    private ProgressDialog progressDialog;
    public static boolean pageLoad = false;
    private LoadingProgress loadingProgress;
    private MountCostRecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private List<MounthCosts> mounthCostsList = new ArrayList<>();
    RestInterface apiService;
    private String condition = "all";
    private boolean loading = true;
    private int page = 1;
    private View v;
    private LinearLayoutManager mLayoutManager;
    private TextView mountfuel;
    ArrayList<String> StringArray = new ArrayList<String>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mounthcostrecycler);
        ButterKnife.bind(this);
        //mountfuel=(TextView)findViewById(R.id.fuelcostjanuary) ;

        userıd = getIntent().getIntExtra("userID", 0);
        gardenıd = getIntent().getIntExtra("gardenID", 0);
        productıd = getIntent().getIntExtra("productID", 0);

        getMounthcosts(productıd, gardenıd);
        recyclerView = findViewById(R.id.recyclerviewmountcost);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setLayoutManager(mLayoutManager);

    }

    private void returnactivity() {
        Intent myIntent = new Intent(this, Cost.class);
        startActivity(myIntent);

    }

    private void getMounthcosts(int productID, int gardenID) {
        progressDialog = new ProgressDialog(MounthCost.this);
        progressDialog.setMessage("bekleyiniz");
        progressDialog.show();
        RestInterface apiService = API.getClient().create(RestInterface.class);
        Call<List<MounthCosts>> call = apiService.getMountcost(productID, gardenID);
        call.enqueue(new Callback<List<MounthCosts>>() {
            @Override
            public void onResponse(Call<List<MounthCosts>> call, Response<List<MounthCosts>> response) {

                if (response.body() == null) {
                    Toast.makeText(getApplicationContext(), "Lütfen tarla ve ürün seçiniz.", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (response.body().size() == 0) {
                    Toast.makeText(getApplicationContext(), "Bu Tarla ve Ürüne ait masraf bulunamadı", Toast.LENGTH_SHORT).show();
                    finish();

                } else if(User_Login.userID>0) {
                       if (response.isSuccessful()) {

                        mounthCostsList = response.body();
                        recyclerAdapter = new MountCostRecyclerAdapter(getBaseContext(), mounthCostsList);
                        recyclerView.setAdapter(recyclerAdapter);
                        recyclerAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<MounthCosts>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Veriler yüklenirken hata oluştu.Lütfen daha sonra tekrar deneyiniz", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
