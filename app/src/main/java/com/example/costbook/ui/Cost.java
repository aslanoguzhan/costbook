package com.example.costbook.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.costbook.R;
import com.example.costbook.adapter.ProfileRecylerAdapter;
import com.example.costbook.adapter.RecyclerAdapter;
import com.example.costbook.pojo.AppUser;
import com.example.costbook.pojo.Gardens;
import com.example.costbook.pojo.Products;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.services.API;
import com.example.costbook.ui.cost_infopop.ChemicalFertilizersinfo;
import com.example.costbook.ui.cost_infopop.MachineCostinfo;
import com.example.costbook.ui.cost_infopop.Costtotalinfo;
import com.example.costbook.ui.cost_infopop.FuelCostinfo;
import com.example.costbook.ui.cost_infopop.LaborforceCostinfo;
import com.example.costbook.ui.cost_infopop.MounthCost;
import com.example.costbook.ui.cost_infopop.OrganicFertilizersinfo;
import com.example.costbook.ui.cost_infopop.Pesticideinfo;
import com.example.costbook.ui.cost_infopop.SeedCostinfo;
import com.example.costbook.ui.cost_infopop.İrrigationinfo;
import com.example.costbook.ui.login.User_Login;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cost extends Fragment {
    private ProgressDialog progressDialog;
    private List<AppUser> lstCosts = new ArrayList<>();
    private LoadingProgress loadingProgress;
    private Spinner getGarden, getProduct;
    RestInterface apiService;
    private Spinner gardenName, productName;
    private List<Gardens> gardens;
    private List<Products> products;
    private RecyclerView recyclerView;
    private int page = 1, randomSize, pageSize = 20, flag = 0, startPage = 1;
    private boolean loading = true;
    Toolbar toolbar;
    private int result = 1;
    private List<String> productNames;
    private List<String> gardenNames;

    private int userID , getGardenID;
    private int getUserId, getProductID;
    public static RecyclerAdapter recyclerAdapter;
    public static boolean pageLoad = false;
    private ProfileRecylerAdapter profileRecylerAdapter;
    private ImageView costadd, totalfuel, totalmachine, totalseed, totalorganic, totalchemical, totalirrigation, totallaberfore, totalpesticide, costotal;
    View v;
    private TextView category;
    private Intent ıntent;

    public Cost() {

    }


    @SneakyThrows
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.cost, container, false);
        costadd = (ImageView) v.findViewById(R.id.costicon);
        totalfuel = (ImageView) v.findViewById(R.id.fuel);
        totalmachine = (ImageView) v.findViewById(R.id.machine);
        totalorganic = (ImageView) v.findViewById(R.id.organic);
        totalchemical = (ImageView) v.findViewById(R.id.Chemical);
        totalseed = (ImageView) v.findViewById(R.id.seed);
        totallaberfore = (ImageView) v.findViewById(R.id.forcelober);
        totalpesticide = (ImageView) v.findViewById(R.id.pesticide);
        totalirrigation = (ImageView) v.findViewById(R.id.irrigation);
        costotal = (ImageView) v.findViewById(R.id.totalcostinformation);
        category = (TextView) v.findViewById(R.id.costtotal);
        getGarden = (Spinner) v.findViewById(R.id.gardeninfoCost);
        getProduct = (Spinner) v.findViewById(R.id.productioninfoCost);


        userID=getActivity().getIntent().getIntExtra("userID", User_Login.userID);
        getGarden(userID);

        costadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myıntent = new Intent(getActivity(), MounthCost.class);
                myıntent.putExtra("userID", User_Login.userID);
                myıntent.putExtra("gardenID", getGardenID);
                myıntent.putExtra("productID", getProductID);
                startActivity(myıntent);
            }
        });
        costotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myıntent1 = new Intent(getActivity(), Costtotalinfo.class);
                myıntent1.putExtra("userID", User_Login.userID);
                myıntent1.putExtra("gardenID", getGardenID);
                myıntent1.putExtra("productID", getProductID);
                startActivity(myıntent1);
            }
        });
        totalfuel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                Intent ıntent = new Intent(getActivity(), FuelCostinfo.class);
                ıntent.putExtra("userID", User_Login.userID);
                ıntent.putExtra("gardenID", getGardenID);
                ıntent.putExtra("productID", getProductID);
                startActivity(ıntent);
            }
        });
        totalmachine.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(getActivity(), MachineCostinfo.class);
                ıntent.putExtra("userID", User_Login.userID);
                ıntent.putExtra("gardenID", getGardenID);
                ıntent.putExtra("productID", getProductID);
                startActivity(ıntent);
            }
        });
        totalseed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(getActivity(), SeedCostinfo.class);
                ıntent.putExtra("userID", User_Login.userID);
                ıntent.putExtra("gardenID", getGardenID);
                ıntent.putExtra("productID", getProductID);
                startActivity(ıntent);
            }
        });
        totalorganic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(getActivity(), OrganicFertilizersinfo.class);
                ıntent.putExtra("userID", User_Login.userID);
                ıntent.putExtra("gardenID", getGardenID);
                ıntent.putExtra("productID", getProductID);
                startActivity(ıntent);
            }
        });
        totalchemical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(getActivity(), ChemicalFertilizersinfo.class);
                ıntent.putExtra("userID", User_Login.userID);
                ıntent.putExtra("gardenID", getGardenID);
                ıntent.putExtra("productID", getProductID);
                startActivity(ıntent);
            }
        });
        totallaberfore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(getActivity(), LaborforceCostinfo.class);
                ıntent.putExtra("userID", User_Login.userID);
                ıntent.putExtra("gardenID", getGardenID);
                ıntent.putExtra("productID", getProductID);
                startActivity(ıntent);
            }
        });
        totalpesticide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(getActivity(), Pesticideinfo.class);
                ıntent.putExtra("userID", User_Login.userID);
                ıntent.putExtra("gardenID", getGardenID);
                ıntent.putExtra("productID", getProductID);
                startActivity(ıntent);
            }
        });
        totalirrigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ıntent = new Intent(getActivity(), İrrigationinfo.class);
                ıntent.putExtra("userID", User_Login.userID);
                ıntent.putExtra("gardenID", getGardenID);
                ıntent.putExtra("productID", getProductID);
                startActivity(ıntent);
            }
        });

        getGarden.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (gardens != null) {

                    getGardenID = gardens.get(position).getGardenID();

                }
                String selectedItem = parent.getItemAtPosition(position).toString();
                getProduction(getGardenID, userID);
                //  Toast.makeText(getContext(), selectedItem , Toast.LENGTH_SHORT).show();

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
                    String selectedItem = parent.getItemAtPosition(position).toString();
                    // Toast.makeText(getContext(), selectedItem , Toast.LENGTH_SHORT).show();

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
                if (response.isSuccessful()) {
                    products = response.body();
                    productNames = new ArrayList<>();
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


    private void getGarden(int userID) {
        apiService = API.getClient().create(RestInterface.class);
        Call<List<Gardens>> call = apiService.getMygarden(userID);

        call.enqueue(new Callback<List<Gardens>>() {
            @Override
            public void onResponse(Call<List<Gardens>> call, Response<List<Gardens>> response) {
                if(User_Login.userID>0){
                if (response.isSuccessful()) {
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
            }

            @Override
            public void onFailure(Call<List<Gardens>> call, Throwable t) {
                Toast.makeText(getContext(), "veriler yüklenirken hata oluştu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void displayReceivedData(String message) {

        Call<List<AppUser>> call = apiService.getAllCategorizes(page, message);
        call.enqueue(new Callback<List<AppUser>>() {
            @Override
            public void onResponse(Call<List<AppUser>> call, Response<List<AppUser>> response) {
                lstCosts = response.body();
                // recyclerAdapter = new RecyclerAdapter(getContext(),lstCosts);
                recyclerView.setAdapter(recyclerAdapter);
                Toast.makeText(getContext(), "1. sayfa", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<AppUser>> call, Throwable t) {

            }


        });

    }

}