package com.example.costbook.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.costbook.R;
import com.example.costbook.stroge.SharedPrefManager;

public class Settings extends AppCompatActivity {

    private ProgressDialog pd;
    private TextView exits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settigsactivity);
        exits=findViewById(R.id.exit);

        exits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //logout();

                Intent intent = new Intent(Settings.this,User_Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
//                pd.setMessage("Çıkış yapılıyor");
//                pd.setCancelable(false);
//                pd.show();
//
//                SharedPreferences preferences = getApplicationContext().getSharedPreferences( "checkbox", MODE_PRIVATE );
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString( "remember", "false");
//                editor.putString( "issigned", "none" );
//                editor.apply();
//                new CountDownTimer( 2000, 1000 ) {
//
//                    @Override
//                    public void onTick(long l) {
//
//                }
//
//                    @Override
//                    public void onFinish() {
//                        finish();
//                        Intent signup = new Intent( Settings.this, User_Login.class );
//                        startActivity( signup );
//                        pd.dismiss();
//
//
//                    }
//                }.start();

            }
        });



    }
    private void logout(){
        SharedPrefManager.getInstance(getApplicationContext()).clear();
        Intent intent = new Intent(getApplicationContext(), User_Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
}
