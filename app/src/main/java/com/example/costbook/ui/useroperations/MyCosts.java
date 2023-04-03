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
import com.example.costbook.adapter.MyCostRecyclerAdapter;
import com.example.costbook.pojo.MyCost;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.services.API;
import com.example.costbook.ui.LoadingProgress;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCosts extends AppCompatActivity {
    private ProgressDialog progressDialog;
    public static boolean pageLoad = false;
    private LoadingProgress loadingProgress;
    private MyCostRecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private List<MyCost> myCostList = new ArrayList<>();
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
        setContentView(R.layout.mycost_recycler);
        userID = getIntent().getIntExtra("userID", 0);

        getAllmycost(userID);

        recyclerView = findViewById(R.id.mycostrecyler);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setLayoutManager(mLayoutManager);


    }
    private void getAllmycost(int userID) {
        Log.i("userID", String.valueOf(userID));
        progressDialog = new ProgressDialog(MyCosts.this);
        progressDialog.setMessage("bekleyiniz");
        progressDialog.show();
        progressDialog.dismiss();
        apiService = API.getClient().create(RestInterface.class);
        Call<List<MyCost>> call = apiService.getAllmycost(userID);
        call.enqueue(new Callback<List<MyCost>>() {
            @Override
            public void onResponse(Call<List<MyCost>> call, Response<List<MyCost>> response) {
                if (response.isSuccessful()) {

                    myCostList = response.body();
                    recyclerAdapter = new MyCostRecyclerAdapter(getBaseContext(), myCostList);
                    recyclerView.setAdapter(recyclerAdapter);
                    recyclerAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();

                }


            }

            @Override
            public void onFailure(Call<List<MyCost>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "hata", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
