package com.example.costbook.ui.gardeninfo;

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

public class EditGarden extends AppCompatActivity {
    private EditText getGardenName, getArea,getAda,
            getParsel,getCity,getTown,getDistrict;
    private int getUserID,getGardenID;


    private Button save;
    private ProgressDialog progressDialog;
    public EditGarden() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editgarden);
        getGardenName=(EditText) findViewById(R.id.tarlaAdıInfo1);
        getArea=(EditText)findViewById(R.id.tarlaalnınfo1);
        getAda=(EditText)findViewById(R.id.adaınfo1);
        getParsel=(EditText)findViewById(R.id.parselınfo1);
        getCity=(EditText)findViewById(R.id.cityınfo1);
        getTown=(EditText)findViewById(R.id.towninfo1);
        getDistrict=(EditText)findViewById(R.id.districtinfo1);
        save=(Button)findViewById(R.id.editgardenbtn);
        getUserID=getIntent().getIntExtra("userID",0);
        getGardenID=getIntent().getIntExtra("gardenID",0);
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
        JSONObject garden = new JSONObject();


        try {


            user.put("userID", getUserID);
            post.put("user", user);
            garden.put("gardenID",getGardenID);
            post.put("garden",garden);
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
        Call<Void> call = postAPI.getRetroClient().updateGarden(post.toString());
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressDialog.dismiss();
                if (response.code()==201) {
                    Toast.makeText(getApplicationContext(), "kayıt güncellendi", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "hata oluştu", Toast.LENGTH_LONG).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Sistem hatası daha sonra tekrar deneyin", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }


        });
    }
}
