package com.example.costbook.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.costbook.R;
import com.example.costbook.pojo.MyGarden;

import java.util.List;

public class MyGardensRecyclerAdapter extends RecyclerView.Adapter<MyGardensRecyclerAdapter.myViewHolder> {
    private static long userID ;
    private static long gardenID ;
    private static long productID ;
    private Context context;
    private List<MyGarden> mygardenlist;
    private int position;
    public static long getGardenID() {
        return gardenID;
    }
    public static long getProductID() {
        return productID;
    }
    public static long getUserID() {
        return userID;
    }

    public  MyGardensRecyclerAdapter(Context context, List<MyGarden> mygardenlist) {
        if (mygardenlist != null)
            this.context = context;
        this.mygardenlist = mygardenlist;


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public MyGardensRecyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.mygarden, parent, false);

        MyGardensRecyclerAdapter.myViewHolder vHolder = new MyGardensRecyclerAdapter.myViewHolder(v, mygardenlist);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyGardensRecyclerAdapter.myViewHolder holder, int position) {
        this.position = position;
        holder.tarlaAdi.setText(String.valueOf(mygardenlist.get(position).getGardenName()));
        holder.ürünAdi.setText(String.valueOf(mygardenlist.get(position).getProductName()));


    }



    @Override
    public int getItemCount() {
        if (mygardenlist != null) return mygardenlist.size();
        else return 0;
    }
    public void addList(List<MyGarden> list_data) {
        for (MyGarden m : list_data) {
            mygardenlist.add(m);
        }
        notifyDataSetChanged();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView tarlaAdi,ürünAdi ;
        private CardView cardView;


        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public myViewHolder(@NonNull View itemView, final List<MyGarden> mygardenlist) {
            super(itemView);
            cardView = itemView.findViewById(R.id.mygardencardview);
            tarlaAdi = (TextView) itemView.findViewById(R.id.mygardenName);
            ürünAdi = (TextView) itemView.findViewById(R.id.myproductName);




        }
    }
}
