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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendCode extends AppCompatActivity {
    String email1,_token;
    String _section;

    private EditText emailadress;
    private Button send;
    ProgressDialog pd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendcode);
        emailadress=findViewById(R.id.email);
        send=findViewById(R.id.sendcode);
        init();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs(emailadress.getText().toString())) {
                    sendemail(emailadress.getText().toString());
                }
                else{
                    if(emailadress.getText().toString().equals("")){
                        emailadress.setError("Lütfen Email Adresinizi giriniz.");
                    }
                    else if(!isemailvalid(emailadress.getText().toString()))
                    {
                        emailadress.setError("Lütfen geçerli bir email adresi giriniz!");
                    }

                }

            }
        });


    }

    private void init() {
        pd = new ProgressDialog(this);

        emailadress=findViewById(R.id.email);
        send=findViewById(R.id.sendcode);
        Intent intent = getIntent();
        _section= (String) intent.getSerializableExtra("section");
        if(_section != null)
        {
            //setpassword.setVisibility(View.GONE);
            emailadress.setHint("Email adresinizi Giriniz.");
            emailadress.setInputType(InputType.TYPE_CLASS_TEXT);
        }

        Uri uri = getIntent().getData();
        if(uri !=null){

            Log.i("uri","null değil");
            _token = uri.getQueryParameter("token");
            email1  = uri.getQueryParameter("email");
            Log.i("uri",_token);

            Log.i("uri",email1);

        }



    }

    private Boolean checkInputs(String email) {
        if(email.equals(""))
        {
            if(email.equals(""))
                emailadress.setError("Lütfen Bu Alanı doldurunuz.");

            return false;

        }



        else {

            return true;

        }
    }
    private static boolean isemailvalid(String _email) {

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(_email);
        return matcher.matches();
    }
    private void sendemail(String _email) {
        pd.setMessage("Lütfen Bekleyiniz...");
        pd.show();
        API checkUserCode = new API();
        RestInterface userApi = checkUserCode.getClient().create(RestInterface.class);
        Call<Void> call = userApi.resetpassword(_email);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200){
                    Toast.makeText(getApplicationContext(), "Emailinize Parola Sıfırla Codu Gönderildi.", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                    Intent ıntent=new Intent(getApplicationContext(),Codelogin.class);
                    ıntent.putExtra("email",emailadress.getText().toString());
                    startActivity(ıntent);
                    finish();
                }

                else if(response.code() == 404){
                    Toast.makeText(getApplicationContext(), "Bu adrese kayıtlı kullancı bulunmamaktadır.", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Lütfen daha sonra tekrar deneyiniz.", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }
                Log.i("resetrequest",response.code()+"");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lütfen daha sonra tekrar deneyiniz.", Toast.LENGTH_LONG).show();
                Log.i("resetrequest",t.getMessage()+"");
                pd.dismiss();

            }
        });
    }
}
