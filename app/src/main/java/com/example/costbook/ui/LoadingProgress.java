package com.example.costbook.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.costbook.R;

import java.util.Objects;

public class LoadingProgress {
    private Activity activity;
   
    private AlertDialog dialog;

    public LoadingProgress(Activity myActivity) {
        activity = myActivity;
    }

    public LoadingProgress(Cost cost) {
    }



    public void startLoading() {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.progress_bar, null));
        builder.setCancelable(true);
        dialog = builder.create();
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(R.color.colorPrimaryDark);
        dialog.show();
    }

    public void dismissProgress() {
        dialog.dismiss();
    }
}


