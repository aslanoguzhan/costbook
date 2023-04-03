package com.example.costbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.costbook.R;
import com.example.costbook.pojo.Gardens;
import com.example.costbook.pojo.Products;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.services.API;
import com.example.costbook.ui.cost_infopop.Costtotalinfo;
import com.example.costbook.ui.login.User_Login;
import com.example.costbook.ui.product_infopop.Totalproductinfo;
import com.example.costbook.ui.record.GardenAddProduct;
import com.example.costbook.ui.record.Productrecord;
import com.example.costbook.ui.unitcost_infopop.Unitcost;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Product extends Fragment {
    RestInterface apiService;
    private ImageView saveproduct, productivity, totalproduct, totalcost,savegardenproduct;
    private Spinner getGarden, getProduct;

    private List<Gardens> gardens;
    private List<Products> products;
    private List<String> productNames;
    private List<String> gardenNames;
    private int userID ,getGardenID;
    private int  getProductID;

    public Product() {
    }


    View v;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.product, container, false);
        productivity = (ImageView) v.findViewById(R.id.verim);
        saveproduct = (ImageView) v.findViewById(R.id.urunekle);
        totalproduct = (ImageView) v.findViewById(R.id.totalproduct);
        totalcost = (ImageView) v.findViewById(R.id.totalcost);
        savegardenproduct=(ImageView)v.findViewById(R.id.gardenproductadd);
        getGarden = (Spinner) v.findViewById(R.id.gardeninfoProduct);
        getProduct = (Spinner) v.findViewById(R.id.productioninfoProduct);

       userID=getActivity().getIntent().getIntExtra("userID", User_Login.userID);

        getAllgarden(userID);

        savegardenproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent =new Intent(getActivity(), GardenAddProduct.class);
                ıntent.putExtra("userID",User_Login.userID);
                ıntent.putExtra("gardenID",getGardenID);
                ıntent.putExtra("productID",getProductID);
                startActivity(ıntent);
            }
        });


        saveproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(getActivity(), Productrecord.class);
                ıntent.putExtra("gardenID",getGardenID);
                ıntent.putExtra("productID",getProductID);
                startActivity(ıntent);
            }
        });
        productivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent1 = new Intent(getActivity(), Unitcost.class);
                ıntent1.putExtra("gardenID",getGardenID);
                ıntent1.putExtra("productID",getProductID);
                startActivity(ıntent1);
            }
        });
        totalproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent2 = new Intent(getActivity(), Totalproductinfo.class);
                ıntent2.putExtra("gardenID",getGardenID);
                ıntent2.putExtra("productID",getProductID);
                startActivity(ıntent2);
            }
        });
        totalcost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myıntent = new Intent(getActivity(), Costtotalinfo.class);
                myıntent.putExtra("gardenID",getGardenID);
                myıntent.putExtra("productID",getProductID);
                startActivity(myıntent);

            }
        });
        getGarden.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (gardens != null) {
                    getGardenID = gardens.get(position).getGardenID();
                }
                String selectedItem=parent.getItemAtPosition(position).toString();
                getProduction(getGardenID,userID);
                //Toast.makeText(getContext(), selectedItem + " seçildi", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "veriler yüklenirken hata oluştu", Toast.LENGTH_SHORT).show();
            }
        });

        getProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (products != null) {


                    getProductID = (int) products.get(position).getProductID();
                    String selectedItem=parent.getItemAtPosition(position).toString();
                   // Toast.makeText(getContext(), selectedItem + " seçildi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return v;
    }



    private void getProduction(int gardenID, int userID) {
        apiService = API.getClient().create(RestInterface.class);
        Call<List<Products>> call = apiService.getGardensproduct(gardenID, userID);
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if(response.isSuccessful()) {
                    products = response.body();
                    productNames = new ArrayList<String>();
                    for (int i = 0; i < products.size(); i++) {
                        productNames.add(products.get(i).getProductName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            getContext(),
                            android.R.layout.simple_spinner_dropdown_item,
                            productNames
                    );
                    getProduct.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Toast.makeText(getContext(), "veriler yüklenirken hata oluştu", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getAllgarden(int userID) {
        apiService = API.getClient().create(RestInterface.class);
        Call<List<Gardens>> call = apiService.getMygarden(userID);

        call.enqueue(new Callback<List<Gardens>>() {
            @Override
            public void onResponse(Call<List<Gardens>> call, Response<List<Gardens>> response) {
                if(response.isSuccessful()) {
                    gardens = response.body();
                    gardenNames = new ArrayList<>();
                    for (int i = 0; i < gardens.size(); i++) {
                        gardenNames.add(gardens.get(i).getGardenName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            getContext(),
                            android.R.layout.simple_spinner_dropdown_item,
                            gardenNames
                    );
                    getGarden.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Gardens>> call, Throwable t) {
                Toast.makeText(getContext(), "veriler yüklenirken hata oluştu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
