package com.example.costbook.ui.record;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.costbook.R;
import com.example.costbook.adapter.RecyclerAdapter;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.services.PostAPI;
import com.example.costbook.ui.login.User_Login;

import org.json.JSONObject;

import java.io.IOException;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Productrecord extends AppCompatActivity {
    private EditText getProductName, getTotalProduction;
    private Button savetotal,calculate;
    private ProgressDialog pDialog;
    RestInterface apiService;
    private long getUserID,getGardenID;
    private static long userID = -1;
    private int result = 1,productıd,gardenıd;
    public Productrecord() {
    }


    @SneakyThrows
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        getProductName = (EditText) findViewById(R.id.productname);

        savetotal = (Button) findViewById(R.id.save);
        gardenıd=getIntent().getIntExtra("gardenID",0);
        productıd=getIntent().getIntExtra("productID",0);
        getUserID =  RecyclerAdapter.getUserID();
        savetotal.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View v) {
                if (checkAllText()) {
                try {
                    postProductMethod();
                }
                catch (IOException e) {

                }
            }
            }
        });

    }
    private boolean checkAllText() {
        if (TextUtils.isEmpty( getProductName.getText().toString() )) {
            getProductName.setError( "Bu alan boş olamaz" );
            Toast.makeText( getApplicationContext(), "Eksik veya hatalı alanları doldurunuz", Toast.LENGTH_SHORT ).show();
            return false;
        }
        return true;
    }
    private void postProductMethod() throws IOException {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Lütfen bekleyin...");
        pDialog.show();
        JSONObject post = new JSONObject();




        try {


            post.put("productName", getProductName.getText().toString());





        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Sistem hatası daha sonra tekrar deneyin...", Toast.LENGTH_LONG).show();
        }

        PostAPI postAPI = new PostAPI();
        Call<String> call = postAPI.getRetroClient().getSaveProduct(post.toString());
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                pDialog.dismiss();
                if(User_Login.userID>0) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "kayıt eklendi", Toast.LENGTH_LONG).show();

                }
                } else {
                    Toast.makeText(getApplicationContext(), "hata oluştu", Toast.LENGTH_LONG).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Sistem hatası daha sonra tekrar deneyin", Toast.LENGTH_LONG).show();
                pDialog.dismiss();
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
