package com.example.costbook.ui.record;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.costbook.R;
import com.example.costbook.adapter.RecyclerAdapter;
import com.example.costbook.pojo.Gardens;
import com.example.costbook.pojo.Products;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.services.API;
import com.example.costbook.services.PostAPI;
import com.example.costbook.ui.login.User_Login;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GardenAddProduct  extends AppCompatActivity {
    private EditText getTotalproduct;
    private Button save;
    private ProgressDialog progressDialog;
    public GardenAddProduct(){ }
    private Spinner getGarden, getProduct;
    private long getUserId, getGardenId, getProductId;
    private List<Gardens> gardens;
    private List<Products> products;
    private List<String> productNames;
    private List<String> gardenNames;

    private int userID = 1, getGardenID;
    private int  getProductID,productID,totalProduction;
    RestInterface apiService;
    @SneakyThrows
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gardenaddproduct);
        getGarden=(Spinner)findViewById(R.id.tarlainfospiner);
        getProduct=(Spinner)findViewById(R.id.ürüninfospiner) ;

        getTotalproduct=(EditText)findViewById(R.id.totalürün);
        save = (Button) findViewById(R.id.save);
        userID = getIntent().getIntExtra("userID", 0);
         getGarden(userID);
        getProductId= RecyclerAdapter.getProductID();
        getGardenId=RecyclerAdapter.getGardenID();
        getUserId = RecyclerAdapter.getUserID();
        save.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View v) {

                try {

                    addgardenproduct();
                } catch (Exception e){

                }
            }
        });

        getGarden.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (gardens != null) {

                    getGardenID = gardens.get(position).getGardenID();

                }
                String selectedItem = parent.getItemAtPosition(position).toString();
                //getProduction(getGardenID,userID);
                getAllProducts();
                Toast.makeText(getApplicationContext(), selectedItem + " seçildi", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "veriler yüklenirken hata oluştu", Toast.LENGTH_SHORT).show();
            }
        });
        getProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (products != null) {


                    getProductID = (int) products.get(position).getProductID();
                    String selectedItem = parent.getItemAtPosition(position).toString();
                    Toast.makeText(getApplicationContext(), selectedItem + " seçildi", Toast.LENGTH_SHORT).show();

                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }
//    private void getProduction(int gardenID, int userID) {
//        apiService = API.getClient().create(RestInterface.class);
//        Call<List<Products>> call = apiService.getGardensproduct(gardenID, userID);
//        call.enqueue(new Callback<List<Products>>() {
//            @Override
//            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
//                if(response.isSuccessful()) {
//                    products = response.body();
//                    productNames = new ArrayList<>();
//                    for (int i = 0; i < products.size(); i++) {
//                        productNames.add(products.get(i).getProductName());
//                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                            getApplicationContext(),
//                            android.R.layout.simple_spinner_dropdown_item,
//                            productNames
//                    );
//                    getProduct.setAdapter(adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Products>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "veriler yüklenirken hata oluştu", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void getAllProducts() {
        apiService = API.getClient().create(RestInterface.class);
        Call<List<Products>> call = apiService.listAllproduct();
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if(response.isSuccessful()) {
                    products = response.body();
                    productNames = new ArrayList<>();
                    for (int i = 0; i < products.size(); i++) {
                        productNames.add(products.get(i).getProductName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            getApplicationContext(),
                            android.R.layout.simple_spinner_dropdown_item,
                            productNames
                    );
                    getProduct.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "veriler yüklenirken hata oluştu", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getGarden(int userID) {
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
                            getApplicationContext(),
                            android.R.layout.simple_spinner_dropdown_item,
                            gardenNames
                    );
                    getGarden.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Gardens>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "veriler yüklenirken hata oluştu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addgardenproduct() throws IOException {
        JSONObject post = new JSONObject();
        JSONObject garden = new JSONObject();
        JSONObject product = new JSONObject();
        JSONObject user = new JSONObject();

       try{
           user.put("userID", getUserId);
           post.put("user", user);
           garden.put("gardenID",getGardenID);
           post.put("garden",garden);
           product.put("productID",getProductID);
           post.put("product",product);

           post.put("totalproduction",getTotalproduct.getText().toString());
       }
       catch (Exception ex){
           Toast.makeText(getApplicationContext(), "Sistem hatası daha sonra tekrar deneyin...", Toast.LENGTH_LONG).show();
       }

        PostAPI postAPI = new PostAPI();
//        LoadingProgress progressDialog1 = new LoadingProgress(this);
//        progressDialog1.startLoading();
        Call<String> call = postAPI.getRetroClient().saveGardenproduct(post.toString());
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(User_Login.userID>0) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "kayıt eklendi", Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(getApplicationContext(), "hata oluştu", Toast.LENGTH_LONG).show();
                }
               finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Sistem hatası daha sonra tekrar deneyin", Toast.LENGTH_LONG).show();

            }
        });
    }

}
