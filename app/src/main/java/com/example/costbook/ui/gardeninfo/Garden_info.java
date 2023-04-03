package com.example.costbook.ui.gardeninfo;

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
import com.example.costbook.adapter.RecyclerAdapter;
import com.example.costbook.pojo.Gardens;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.services.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Garden_info extends AppCompatActivity {
    private ProgressDialog progressDialog;
    public static boolean pageLoad = false;
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private List<Gardens> gardenList = new ArrayList<>();
    RestInterface apiService;
    private String condition = "all";
    private boolean loading = true;
    private int page = 1;
    private Gardens gardens;
    private View v;
    private int userId;
    public static int gardenıd;
    private LinearLayoutManager mLayoutManager;
    private ImageView addgardencost;
    private ImageView edit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garden_reycler);
        userId = getIntent().getIntExtra("userID", 0);

        getAllGarden(userId);

        recyclerView = findViewById(R.id.recyclerviewGarden);

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setLayoutManager(mLayoutManager);



    }

    private void getAllGarden(int userID) {
        Log.i("userID", String.valueOf(userID));
        progressDialog = new ProgressDialog(Garden_info.this);
        progressDialog.setMessage("bekleyiniz");
        progressDialog.show();
        progressDialog.dismiss();
        apiService = API.getClient().create(RestInterface.class);
        Call<List<Gardens>> call = apiService.getGarden(userID);
        call.enqueue(new Callback<List<Gardens>>() {
            @Override
            public void onResponse(Call<List<Gardens>> call, Response<List<Gardens>> response) {
                if (response.isSuccessful()) {
                    gardenList = response.body();
                    recyclerAdapter = new RecyclerAdapter(getBaseContext(), gardenList,userId);
                    recyclerView.setAdapter(recyclerAdapter);
                    recyclerAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();

                }


            }

            @Override
            public void onFailure(Call<List<Gardens>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "hata", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
