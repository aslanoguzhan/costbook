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
import com.example.costbook.pojo.MyProduct;

import java.util.List;

public class MyProductsRecyclerAdapter extends RecyclerView.Adapter<MyProductsRecyclerAdapter.myViewHolder>  {
    private static long userID = 1;
    private static long gardenID ;
    private static long productID ;
    private Context context;
    private List<MyProduct> myProductList;
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

    public  MyProductsRecyclerAdapter(Context context, List<MyProduct> myProductList) {
        if (myProductList != null)
            this.context = context;
        this.myProductList = myProductList;


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public MyProductsRecyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.myproduct, parent, false);

        MyProductsRecyclerAdapter.myViewHolder vHolder = new MyProductsRecyclerAdapter.myViewHolder(v, myProductList);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyProductsRecyclerAdapter.myViewHolder holder, int position) {
        this.position = position;
        holder.tarlaAdi.setText(String.valueOf(myProductList.get(position).getGardenName()));
        holder.ürünAdi.setText(String.valueOf(myProductList.get(position).getProductName()));
        holder.üretim.setText(String.valueOf(myProductList.get(position).getProduction()) +"KG");


    }



    @Override
    public int getItemCount() {
        if (myProductList != null) return myProductList.size();
        else return 0;
    }
    public void addList(List<MyProduct> list_data) {
        for (MyProduct m : list_data) {
            myProductList.add(m);
        }
        notifyDataSetChanged();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView tarlaAdi,ürünAdi,üretim;
        private CardView cardView;


        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public myViewHolder(@NonNull View itemView, final List<MyProduct> myproductlist) {
            super(itemView);
            cardView = itemView.findViewById(R.id.myproductcardview);
            tarlaAdi = (TextView) itemView.findViewById(R.id.mygardenName1);
            ürünAdi = (TextView) itemView.findViewById(R.id.myproductName1);
            üretim=(TextView)itemView.findViewById(R.id.miktarinfo);




        }
    }
}
