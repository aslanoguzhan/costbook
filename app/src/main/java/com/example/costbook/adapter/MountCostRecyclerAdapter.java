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
import com.example.costbook.pojo.MounthCosts;

import java.util.List;

public class MountCostRecyclerAdapter extends RecyclerView.Adapter<MountCostRecyclerAdapter.myViewHolder> {
    private static long userID = 1;
    private static long gardenID ;
    private static long productID ;
    private Context context;
    private List<MounthCosts> mounthCostsList;
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

    public  MountCostRecyclerAdapter(Context context, List<MounthCosts> mounthCostsList) {
        if (mounthCostsList != null)
            this.context = context;
        this.mounthCostsList = mounthCostsList;


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public MountCostRecyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.mountcost1, parent, false);

        MountCostRecyclerAdapter.myViewHolder vHolder = new MountCostRecyclerAdapter.myViewHolder(v, mounthCostsList);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MountCostRecyclerAdapter.myViewHolder holder, int position) {
        this.position = position;
        holder.ay.setText(String.valueOf(mounthCostsList.get(position).getAy()));
        holder.yıl.setText(String.valueOf(mounthCostsList.get(position).getYıl()));
        holder.seed.setText(String.valueOf(mounthCostsList.get(position).getSeed()) + " TL");
        holder.fuel.setText(String.valueOf(mounthCostsList.get(position).getFuel()) + " TL");
        holder.ırrıgation.setText(String.valueOf(mounthCostsList.get(position).getIrrigation()) + " TL");
        holder.laborforce.setText(String.valueOf(mounthCostsList.get(position).getLaborforce())  + " TL");
        holder.machine.setText(String.valueOf(mounthCostsList.get(position).getMachine())+ "TL");
        holder.organikfertilizers.setText(String.valueOf(mounthCostsList.get(position).getOrganicfertilizers())+ " TL");
        holder.chemicalfertizilers.setText(String.valueOf(mounthCostsList.get(position).getChemicalfertilizers())+ " TL");
        holder.pesticide.setText(String.valueOf(mounthCostsList.get(position).getPesticide())+ " TL");

    }



    @Override
    public int getItemCount() {
        if (mounthCostsList != null) return mounthCostsList.size();
        else return 0;
    }
    public void addList(List<MounthCosts> list_data) {
        for (MounthCosts m : list_data) {
            mounthCostsList.add(m);
        }
        notifyDataSetChanged();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView ay, yıl, seed, fuel, ırrıgation, laborforce, machine,organikfertilizers,chemicalfertizilers,pesticide;
        private CardView cardView;


        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public myViewHolder(@NonNull View itemView, final List<MounthCosts> mounthCostsList1) {
            super(itemView);
            cardView = itemView.findViewById(R.id.gardencardview1);
            ay = (TextView) itemView.findViewById(R.id.mountinfo);
            yıl = (TextView) itemView.findViewById(R.id.yearinfo);
            seed = (TextView) itemView.findViewById(R.id.tohuminfo);
            fuel = (TextView) itemView.findViewById(R.id.yakıtinfo);
            ırrıgation = (TextView) itemView.findViewById(R.id.sulamainfo);
            laborforce = (TextView) itemView.findViewById(R.id.isgücüinfo);
            machine = (TextView) itemView.findViewById(R.id.makınainfo);
            organikfertilizers = (TextView) itemView.findViewById(R.id.organikgübreinfo);
            chemicalfertizilers = (TextView) itemView.findViewById(R.id.kimyasalgübreinfo);
            pesticide = (TextView) itemView.findViewById(R.id.ilacinfo);



        }
    }

}
        