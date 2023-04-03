package com.example.costbook.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.costbook.R;
import com.example.costbook.pojo.CustomUser;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.services.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Codelogin extends AppCompatActivity {

    private Button resetpw;
    ProgressDialog pd;
    EditText mycode;
    CustomUser customUser;
    String emailadress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.codelogin);
        resetpw = findViewById(R.id.resedpasword);
        mycode = findViewById(R.id.checkmycode);
        emailadress=getIntent().getStringExtra("email");
        init();
        resetpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInputs(mycode.getText().toString())){


                    checkUserCode(mycode.getText().toString());
                    finish();
                }
            }
        });
    }

    public void checkUserCode(String code) {
        pd.setMessage("Lütfen Bekleyiniz...");
        pd.setCancelable(false);
        pd.show();
        API checkcode = new API();
        RestInterface userApi = checkcode.getClient().create(RestInterface.class);
        Call<Void> call = userApi.checkusercode(emailadress,code);

        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Kod doğrulandı.", Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(Codelogin.this, Resetpassword.class);
                    myIntent.putExtra("token",mycode.getText().toString());
                    myIntent.putExtra("email",emailadress);
                    startActivity(myIntent);
                    pd.dismiss();
                } else {
                    pd.dismiss();
                    Log.i("kod", response.code() + "");
                    for (int i = 0; i < 2; i++)
                        Toast.makeText(getApplicationContext(), "Girdiğiniz Kod yanlış. Lütfen Tekrar Deneyin.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                pd.dismiss();
                Log.i("navint", "failure");

            }
        });
    }

    public void init() {
        pd = new ProgressDialog(Codelogin.this);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("token", MODE_PRIVATE);

        mycode = findViewById(R.id.checkmycode);
        resetpw = findViewById(R.id.resedpasword);
        Bundle extras = getIntent().getExtras();
        Intent i = getIntent();
        customUser = (CustomUser) i.getSerializableExtra("user");
        if (customUser == null)
            Log.i("instance", "evet null");
    }

    private Boolean checkInputs(String code) {
        if (code.equals("")) {
            if (code.equals(""))
                mycode.setError("Lütfen Kodu Giriniz.");

            return false;

        } else {

            return true;

        }
    }


}
