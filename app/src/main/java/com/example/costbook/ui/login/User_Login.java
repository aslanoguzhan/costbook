package com.example.costbook.ui.login;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.costbook.R;
import com.example.costbook.pojo.AppUser;
import com.example.costbook.pojo.CustomUser;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.services.API;
import com.example.costbook.ui.useroperations.MyCosts;
import com.example.costbook.ui.useroperations.MyGardens;
import com.example.costbook.ui.useroperations.MyProduts;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class User_Login extends Fragment {

    View v;
    private boolean flag = false;


    public User_Login() {
    }

    public static boolean pageLoad = false;
    private TextView sign_up, resetpassword, name;
    private EditText eEmail;
    private EditText ePassword;
    private Button eLogin;
    private Boolean rememberMe = false;
    private ProgressDialog progressDialog;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    CustomUser customUser;
    AppUser user;

    private String username;
    public static int userID;


    @SuppressLint("CommitPrefEdits")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        if (!rememberMe) {
            v = inflater.inflate(R.layout.user_login, container, false);
            ButterKnife.bind(this, v);
            eEmail = (EditText) v.findViewById(R.id.et_email);
            ePassword = (EditText) v.findViewById(R.id.et_password);
            eLogin = (Button) v.findViewById(R.id.btn_login);
            sign_up = (TextView) v.findViewById(R.id.register);
            resetpassword = (TextView) v.findViewById(R.id.forgetpassword);

            sign_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myıntent = new Intent(getActivity(), Register.class);
                    startActivity(myıntent);
                }
            });
            eLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkUserInput()) {
                        userStandardLogin(eEmail.getText().toString(), ePassword.getText().toString());
                    }

                }
            });
            resetpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent ıntent = new Intent(getActivity(), SendCode.class);
                    ıntent.putExtra("section", "giris");
                    startActivity(ıntent);


                }
            });


            preferences = getActivity().getSharedPreferences("rememberMe", MODE_PRIVATE);
            rememberMe = preferences.getBoolean("remember", false);
            editor = preferences.edit();

        } else if (rememberMe) {
            v = inflater.inflate(R.layout.profil, container, false);

            TextView mycost = v.findViewById(R.id.mycosts);
            TextView mygardanname = v.findViewById(R.id.mygardanname);
            ImageView garden = v.findViewById(R.id.gardenpicture);
            name = v.findViewById(R.id.name);
            TextView exits = v.findViewById(R.id.exitsıcon);
            TextView productions = v.findViewById(R.id.myproducts);


            name.setText(username);


            mygardanname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ıntent = new Intent(getActivity(), MyGardens.class);
                    ıntent.putExtra("userID", userID);
                    startActivity(ıntent);

                }
            });
            productions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ıntemt = new Intent(getActivity(), MyProduts.class);
                    ıntemt.putExtra("userID", userID);
                    startActivity(ıntemt);
                }
            });
            exits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userID = -1;
                    editor.clear();
                    editor.commit();
                    rememberMe = false;
                    refreshPage();
                    Toast.makeText(getContext(), "Oturum kapandı", Toast.LENGTH_SHORT).show();


                }
            });
            mycost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ıntent = new Intent(getActivity(), MyCosts.class);
                    ıntent.putExtra("userID", userID);
                    startActivity(ıntent);
                }
            });


        }

        return v;

    }


    private void userStandardLogin(String email, String password) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Giriş yapılıyor");
        progressDialog.show();


        RestInterface apiService = API.getClient().create(RestInterface.class);
        Call<CustomUser> call = apiService.checkStandard(email, password);
        call.enqueue(new Callback<CustomUser>() {
            @Override
            public void onResponse(Call<CustomUser> call, Response<CustomUser> response) {

                if (response.code()==200) {

                    userID = response.body().getUserID();
                    String a;
                    a = response.body().getUserName();
                    username = a;
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Giriş basarılı", Toast.LENGTH_SHORT).show();
                    preferences = getContext().getSharedPreferences("rememberMe", MODE_PRIVATE);
                    editor.putBoolean("remember", true);
                    rememberMe=true;
                    editor.apply();
                    flag = true;
                    refreshPage();

                } else if (response.code() == 401) {
                    Toast.makeText(getContext(), "Email adresiniz veya şifreniz hatalı", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }else if(response.code()==404){
                    Toast.makeText(getContext(), "Böyle bir kullanıcı bulunamadı.", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }


            }

            @Override
            public void onFailure(Call<CustomUser> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Bir sorun oluştu daha sonra tekrar deneyiniz.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public Boolean checkUserInput() {
        Boolean check = true;
        if (eEmail.getText().toString().equals("")) {
            eEmail.setError("Lütfen Bu Alanı Doldurun");
            check = false;
        }
        if (ePassword.getText().toString().equals("") || ePassword.length() < 6) {
            ePassword.setError("Lütfen Bu Alanı Doldurun");
            ePassword.setError("Şifreniz En Az 6 Karakter Olmalı");
            check = false;
        }
        return check;
    }

    private void refreshPage() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(User_Login.this).attach(User_Login.this).commit();
    }


}
