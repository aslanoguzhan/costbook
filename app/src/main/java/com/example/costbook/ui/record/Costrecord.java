package com.example.costbook.ui.record;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.costbook.R;
import com.example.costbook.adapter.RecyclerAdapter;
import com.example.costbook.pojo.Costs;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.services.PostAPI;
import com.example.costbook.ui.LoadingProgress;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Costrecord extends AppCompatActivity {
    private EditText getMecanic, getSeed, getChemicalDrug, getChemicalFertilizers, getOrganicFertilizers, getLaborForce, getİrrigation, getFuel;
    private Button save;
    private ProgressDialog progressDialog;
    private List<Costs> lstCosts = new ArrayList<>();
    private LoadingProgress loadingProgress;
   // private static long userID = 1;

    RestInterface apiService;
    private RecyclerView recyclerView;
    private int page = 1, randomSize, pageSize = 20, flag = 0, startPage = 1;
    private boolean loading = true;
    Toolbar toolbar;
    public static RecyclerAdapter recyclerAdapter;
    public static boolean pageLoad = false;
    private int result = 1;

    private long getUserId,getProductId,getGardenId;

    public Costrecord() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_cost);
        getMecanic = (EditText) findViewById(R.id.machinecost);
        getSeed = (EditText) findViewById(R.id.seedcost);
        getChemicalDrug = (EditText) findViewById(R.id.chemicalcost);
        getChemicalFertilizers = (EditText) findViewById(R.id.fertilizerscost);
        getOrganicFertilizers = (EditText) findViewById(R.id.organicfertilizerscost);
        getLaborForce = (EditText) findViewById(R.id.laborforcecost);
        getFuel = (EditText) findViewById(R.id.fuelcost);
        getİrrigation = (EditText) findViewById(R.id.watercost);
        save = (Button) findViewById(R.id.save);


        getUserId =  RecyclerAdapter.getUserID();
        getGardenId =  RecyclerAdapter.getGardenID();
        getProductId =  RecyclerAdapter.getProductID();
        save.setOnClickListener(new View.OnClickListener() {

            @SneakyThrows
            @Override
            public void onClick(View v) {
                try {
                    postRequestMethod();
                } catch (Exception e) {

                }


            }


        });


    }


    private void postRequestMethod() throws IOException {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Lütfen bekleyin...");
        progressDialog.show();

        JSONObject post = new JSONObject();
        JSONObject user = new JSONObject();
        JSONObject garden = new JSONObject();
        JSONObject product = new JSONObject();


        try {
            user.put("userID", getUserId);
            post.put("user", user);
            garden.put("gardenID", getGardenId);
            post.put("garden", garden);
            product.put("productID", getProductId);
            post.put("product", product);

            post.put("machine",getMecanic.getText().toString());
            post.put("seed",getSeed.getText().toString());
            post.put("pesticide",getChemicalDrug.getText().toString());
            post.put("chemicalfertilizers",getChemicalFertilizers.getText().toString());
            post.put("organicfertilizers",getOrganicFertilizers.getText().toString());
            post.put("laborforce",getLaborForce.getText().toString());
            post.put("fuel",getFuel.getText().toString());
            post.put("irrigation",getİrrigation.getText().toString());

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Sistem hatası daha sonra tekrar deneyin...", Toast.LENGTH_LONG).show();
        }


        PostAPI postAPI = new PostAPI();
        Call<String> call = postAPI.getRetroClient().getSaveCost(post.toString());
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "kayıt eklendi", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "hata oluştu", Toast.LENGTH_LONG).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Sistem hatası daha sonra tekrar deneyin", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }


        });

    }

    @SuppressLint("SetTextI18n")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == result) {
            if (resultCode == Activity.RESULT_OK) {
                int flag = 0;
                flag = data.getIntExtra("result", flag);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

