package com.example.costbook.ui.useroperations;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.costbook.R;
import com.example.costbook.adapter.MyProductsRecyclerAdapter;
import com.example.costbook.pojo.MyProduct;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.services.API;
import com.example.costbook.ui.LoadingProgress;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProduts extends AppCompatActivity {

    private ProgressDialog progressDialog;
    public static boolean pageLoad = false;
    private LoadingProgress loadingProgress;
    private MyProductsRecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private List<MyProduct> myProductList = new ArrayList<>();
    RestInterface apiService;
    private String condition = "all";
    private boolean loading = true;
    private int page = 1;
    private View v;
    private int userID;
    private LinearLayoutManager mLayoutManager;
    private ImageView addgardencost;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myproduct_recycler);
        userID = getIntent().getIntExtra("userID", 0);

        getAllmyproduct(userID);

        recyclerView = findViewById(R.id.myproductrecyler);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setLayoutManager(mLayoutManager);


    }
    private void getAllmyproduct(int userID) {
        Log.i("userID", String.valueOf(userID));
        progressDialog = new ProgressDialog(MyProduts.this);
        progressDialog.setMessage("bekleyiniz");
        progressDialog.show();
        progressDialog.dismiss();
        apiService = API.getClient().create(RestInterface.class);
        Call<List<MyProduct>> call = apiService.getAllmyproduct(userID);
        call.enqueue(new Callback<List<MyProduct>>() {
            @Override
            public void onResponse(Call<List<MyProduct>> call, Response<List<MyProduct>> response) {
                if (response.isSuccessful()) {

                    myProductList = response.body();
                    recyclerAdapter = new MyProductsRecyclerAdapter(getBaseContext(), myProductList);
                    recyclerView.setAdapter(recyclerAdapter);
                    recyclerAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();

                }


            }

            @Override
            public void onFailure(Call<List<MyProduct>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "hata", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
