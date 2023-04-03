package com.example.costbook.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.costbook.R;
import com.example.costbook.pojo.AppUser;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.services.API;
import com.example.costbook.stroge.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
  private EditText username,userEmail,userpassword,reuserpasword;
  private Button register;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);
        username=findViewById(R.id.et_name);
        register=findViewById(R.id.btn_register);
        userpassword=findViewById(R.id.et_password);
        reuserpasword=findViewById(R.id.et_repassword);
        userEmail=findViewById(R.id.et_email);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkCredentials()){
                    userSignUp(userEmail.getText().toString(),userpassword.getText().toString());
                };

            }
        });




    }
    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, User_Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void userSignUp(String email, String password) {
        final ProgressDialog pd = new ProgressDialog( Register.this );
        pd.setMessage( "Lütfen Bekleyiniz..." );
        pd.show();
        AppUser user = new AppUser( email,password,username.getText().toString());
        API userLogin = new API();
        RestInterface userApi = userLogin.getClient().create( RestInterface.class );
        Call<Void> call = userApi.insertUser( user );
        call.enqueue( new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                pd.dismiss();
                if (response.isSuccessful()) {

                    Toast.makeText(getApplicationContext(),"Üye olundu.",Toast.LENGTH_LONG).show();

                } else {

                        Toast.makeText( getApplicationContext(), "Böyle bir kullanıcı zaten var.", Toast.LENGTH_LONG ).show();
                }
               finish();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Sistem hatası daha sonra tekrar deneyin", Toast.LENGTH_LONG).show();
                pd.dismiss();
            }
        } );


    }




    public Boolean checkCredentials(){
        Boolean check = true;
        if(username.getText().toString().equals("")) {
            username.setError("Lütfen Bu Alanı Boş Bırakmayınız");
            check=false;
        }
        if(userEmail.getText().toString().equals("")) {
            userEmail.setError("Lütfen Bu Alanı Boş Bırakmayınız");
            check=false;
        }
        if(userpassword.getText().toString().equals("")||userpassword.length()<6) {
            userpassword.setError("Lütfen Bu Alanı Boş Bırakmayınız");
            userpassword.setError("Şifreniz En Az 6 Karakter Olmalı");
            check=false;
        }
        if(reuserpasword.getText().toString().equals("")) {
            reuserpasword.setError("Lütfen Bu Alanı Boş Bırakmayınız");
            check=false;
        }

        if(!userpassword.getText().toString().equals(reuserpasword.getText().toString())){
            userpassword.setError("Parolalar Aynı Olmalıdır!");
            reuserpasword.setError("Parolalar Aynı Olmalıdır!");
            check=false;
        }

        return check;
    }

}
