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
import com.example.costbook.pojo.MyCost;

import java.util.List;

public class MyCostRecyclerAdapter extends RecyclerView.Adapter<MyCostRecyclerAdapter.myViewHolder>  {

    private static long userID = 1;
    private static long gardenID ;
    private static long productID ;
    private Context context;
    private List<MyCost> myCostList;
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

    public  MyCostRecyclerAdapter(Context context, List<MyCost> myCostList) {
        if (myCostList != null)
            this.context = context;
        this.myCostList = myCostList;


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public MyCostRecyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.mycost, parent, false);

        MyCostRecyclerAdapter.myViewHolder vHolder = new MyCostRecyclerAdapter.myViewHolder(v, myCostList);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCostRecyclerAdapter.myViewHolder holder, int position) {
        this.position = position;
        holder.tarlaAdi.setText(String.valueOf(myCostList.get(position).getGardenName()));
        holder.ürünAdi.setText(String.valueOf(myCostList.get(position).getProductName()));
        holder.masraf.setText(String.valueOf(myCostList.get(position).getTotalcost()) + "TL");


    }



    @Override
    public int getItemCount() {
        if (myCostList != null) return myCostList.size();
        else return 0;
    }
    public void addList(List<MyCost> list_data) {
        for (MyCost m : list_data) {
            myCostList.add(m);
        }
        notifyDataSetChanged();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView tarlaAdi,ürünAdi,masraf ;
        private CardView cardView;


        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public myViewHolder(@NonNull View itemView, final List<MyCost> myCostList) {
            super(itemView);
            cardView = itemView.findViewById(R.id.mycostcardview);
            tarlaAdi = (TextView) itemView.findViewById(R.id.mygardenName2);
            ürünAdi = (TextView) itemView.findViewById(R.id.myproductName2);
            masraf=(TextView) itemView.findViewById(R.id.miktarinfo2);




        }
    }
}
