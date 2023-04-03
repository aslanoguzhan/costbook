package com.example.costbook.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.costbook.R;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.ui.gardeninfo.Garden_info;
import com.example.costbook.ui.login.User_Login;
import com.example.costbook.ui.record.GardenAdd;
import com.example.costbook.ui.record.GardenAddCost;

public class Garden extends Fragment {
    public Garden(){}
    private ImageView mygarden,savegardencost,addgarden;
    private  int getGardenID,getProductID,userıd;
    private Boolean rememberMe=false;
    private ProgressDialog progressDialog;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String email,password;
    RestInterface apiService;


    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.garden, container, false);
        mygarden=(ImageView)v.findViewById(R.id.mygarden);
        savegardencost=(ImageView)v.findViewById(R.id.addgardencost);
        addgarden=(ImageView)v.findViewById(R.id.gardenekle);


        mygarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ıntent=new Intent(getActivity(), Garden_info.class);
                ıntent.putExtra("userID", User_Login.userID);
                startActivity(ıntent);

            }
        });

        savegardencost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myıntent=new Intent(getActivity(), GardenAddCost.class);
                myıntent.putExtra("gardenID",getGardenID);
                myıntent.putExtra("userID", User_Login.userID);
                myıntent.putExtra("productID",getProductID);
                startActivity(myıntent);
            }
        });
        addgarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent1=new Intent(getActivity(), GardenAdd.class);
                ıntent1.putExtra("userID", User_Login.userID);
                startActivity(ıntent1);
            }
        });

        return v;
    }




}
