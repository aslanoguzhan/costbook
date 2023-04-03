package com.example.costbook.retrofit;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.costbook.R;
import com.example.costbook.adapter.ViewPagerAdapter;
import com.example.costbook.ui.Cost;
import com.example.costbook.ui.Garden;
import com.example.costbook.ui.Product;
import com.example.costbook.ui.login.User_Login;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout = findViewById(R.id.tab_layout);


        ViewPager viewPager = findViewById(R.id.view_pager);


        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(new Cost(), "Masraflar");
        viewPagerAdapter.addFragment(new Product(), "Ürünler");
        viewPagerAdapter.addFragment(new Garden(), "Tarlalar");
        viewPagerAdapter.addFragment(new User_Login(), "Giriş");

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.tl_logo);
        tabLayout.getTabAt(1).setIcon(R.drawable.urun);
        tabLayout.getTabAt(2).setIcon(R.drawable.gardenicon);
        tabLayout.getTabAt(3).setIcon(R.drawable.usericon);



    }


}