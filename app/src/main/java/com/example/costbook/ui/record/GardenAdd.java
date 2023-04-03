package com.example.costbook.ui.record;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.costbook.R;
import com.example.costbook.services.PostAPI;

import org.json.JSONObject;

import java.io.IOException;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GardenAdd extends AppCompatActivity {
    private EditText getGardenName, getArea,getAda,
            getParsel,getCity,getTown,getDistrict;
    private long getUserID;
   // private static  long userID=1;

    private Button save;
    private ProgressDialog progressDialog;
    public GardenAdd() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gardenadd);
        getGardenName=(EditText) findViewById(R.id.tarlaAdıInfo);
        getArea=(EditText)findViewById(R.id.tarlaalnınfo);

        getAda=(EditText)findViewById(R.id.adaınfo);
        getParsel=(EditText)findViewById(R.id.parselınfo);
        getCity=(EditText)findViewById(R.id.cityınfo);
        getTown=(EditText)findViewById(R.id.towninfo);
        getDistrict=(EditText)findViewById(R.id.districtinfo);
        save=(Button)findViewById(R.id.addgarden);
        getUserID=getIntent().getIntExtra("userID",0);
        //getUserID = RecyclerAdapter.getUserID();
        save.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View v) {
                if(checkAllText()) {
                    try {
                        postRequestMethod();
                    } catch (Exception e) {

                    }
                }
            }
        });




    }

    private boolean checkAllText() {
        if (TextUtils.isEmpty( getGardenName.getText().toString() )) {
            getGardenName.setError( "Bu alan boş olamaz" );
            Toast.makeText( getApplicationContext(), "Eksik veya hatalı alanları doldurunuz", Toast.LENGTH_SHORT ).show();
            return false;
        }

        return true;
    }
    private void postRequestMethod() throws IOException {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Lütfen bekleyin...");
        progressDialog.show();

        JSONObject post = new JSONObject();
        JSONObject user = new JSONObject();


        try {


            user.put("userID", getUserID);
            post.put("user", user);
            post.put("gardenName", getGardenName.getText().toString());
            post.put("ada", getAda.getText().toString());
            post.put("parsel", getParsel.getText().toString());
            post.put("city", getCity.getText().toString());
            post.put("town", getTown.getText().toString());
            post.put("district", getDistrict.getText().toString());
            post.put("area",getArea.getText().toString());





        }
        catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Sistem hatası daha sonra tekrar deneyin...", Toast.LENGTH_LONG).show();
        }


        PostAPI postAPI = new PostAPI();
        Call<String> call = postAPI.getRetroClient().getSaveGarden(post.toString());
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "kayıt eklendi", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "hata oluştu", Toast.LENGTH_LONG).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Sistem hatası daha sonra tekrar deneyin", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }


        });
    }
}
