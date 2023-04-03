package com.example.costbook.ui.gardeninfo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.costbook.R;
import com.example.costbook.services.PostAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class deletegarden_pop extends AppCompatActivity {
    private Button deletegardn, close;
    private ProgressDialog progressDialog;
    private int gardenID,userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletegardenpopup);
        deletegardn = findViewById(R.id.yesbtn);
        close = findViewById(R.id.nobtn);
        windowSize();
        userID=getIntent().getIntExtra("userID",0);
        gardenID=getIntent().getIntExtra("gardenID",0);
        deletegardn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletepostgarden(gardenID,userID);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void windowSize() {
        ConstraintLayout constraintLayout = findViewById(R.id.deleteselect);
        ViewGroup.LayoutParams LayoutParams = constraintLayout.getLayoutParams();
        getWindow().setLayout(LayoutParams.width, LayoutParams.height);
        getWindow().setBackgroundDrawableResource(R.drawable.pop_up_frame);
    }

    public void deletepostgarden(int gardenID, int userID) {
        progressDialog = new ProgressDialog( this );
        progressDialog.setMessage( "Lütfen bekleyiniz" );
        progressDialog.show();

        PostAPI postAPI = new PostAPI();
        Call<Void> call = postAPI.getRetroClient().deleteGarden(gardenID, userID);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "kayıt silindi", Toast.LENGTH_LONG).show();
                    finish();
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
