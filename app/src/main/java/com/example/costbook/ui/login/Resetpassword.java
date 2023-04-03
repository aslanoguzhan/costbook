package com.example.costbook.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.costbook.R;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.services.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Resetpassword extends AppCompatActivity {
    ProgressDialog pd;
    String email,token;
    String _section;
    private EditText setpassword,setrepassword;
    private Button sendpassword;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpassword);
        setpassword=findViewById(R.id.nwpassword);
        setrepassword=findViewById(R.id.nwrepassword);
        sendpassword=findViewById(R.id.resetpassword);
        email=getIntent().getStringExtra("email");
        token=getIntent().getStringExtra("token");
        init();

        sendpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkInputs(setpassword.getText().toString(),setrepassword.getText().toString()))
                    changepassword();


            }
        });



    }
    private Boolean checkInputs(String nwpw, String repw) {
        if(nwpw.equals("") || repw.equals(""))
        {

            if(nwpw.equals(""))
                setpassword.setError("Lütfen Bu Alanı Doldurunuz.");
            if(repw.equals(""))
                setrepassword.setError("Lütfen Bu Alanı Doldurunuz.");
            return false;

        }
        else if (nwpw.equals(setrepassword)){
            setrepassword.setError("Parolalar Aynı Olmalıdır.");

            return true;
        }

        else {

            return true;

        }
    }
    private void init() {
        pd = new ProgressDialog(this);

        setpassword=findViewById(R.id.nwpassword);
        setrepassword=findViewById(R.id.nwrepassword);
        Intent intent = getIntent();
        _section= (String) intent.getSerializableExtra("section");
        if(_section != null)
        {
            //setpassword.setVisibility(View.GONE);
            setrepassword.setHint("Şifrenizi Giriniz.");
            setrepassword.setInputType(InputType.TYPE_CLASS_TEXT);
        }

        Uri uri = getIntent().getData();
        if(uri !=null){

            Log.i("uri","null değil");
            token = uri.getQueryParameter("token");
            email  = uri.getQueryParameter("email");
            Log.i("uri",token);

            Log.i("uri",email);

        }



    }


    private void changepassword() {
        pd.setMessage("Lütfen Bekleyiniz...");
        pd.show();
        Log.i("reset","change password methodu");
        API checkUserCode = new API();
        RestInterface userApi = checkUserCode.getClient().create(RestInterface.class);
        Call<Void> call = userApi.setpassword(email,token,setpassword.getText().toString());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.isSuccessful()){

                    Toast.makeText(getApplicationContext(), "Parolanız başarıyla değiştirildi. Giriş yapabilirsiniz.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Resetpassword.this,User_Login.class));
                    pd.dismiss();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Lütfen daha sonra tekrar deneyin...", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }


            }


            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();
                pd.dismiss();
            }
        });

    }



}
