package com.example.costbook.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.costbook.R;
import com.example.costbook.pojo.Gardens;
import com.example.costbook.retrofit.RestInterface;
import com.example.costbook.ui.LoadingProgress;
import com.example.costbook.ui.gardeninfo.EditGarden;
import com.example.costbook.ui.gardeninfo.deletegarden_pop;
import com.example.costbook.ui.login.User_Login;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewHolder> {

    private static int userID = 1;
    private static int gardenID;
    private static int productID;
    private static int costId;
    private Context context;
    private RestInterface apiService;
    private ArrayList<String> compare;
    private List<Gardens> gardensList;
    private ProgressDialog progressDialog;
    private LoadingProgress loadingProgress;
    private int GLPosition;
    private LinearLayout expandableView;
    private int position;
    private ImageView edit;

    public static int getGardenID() {
        return gardenID;
    }

    public static int getProductID() {
        return productID;
    }

    public static int getUserID() {
        return userID;
    }

    public RecyclerAdapter(Context context, List<Gardens> gardensList, int userID) {
        if (gardensList != null)
            this.context = context;
        this.gardensList = gardensList;
        RecyclerAdapter.userID = userID;


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public RecyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.garden_info, parent, false);
        myViewHolder vHolder = new myViewHolder(v, gardensList);


        return vHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        this.position = position;
        holder.gardenName.setText(gardensList.get(position).getGardenName());
        holder.area.setText(String.valueOf(gardensList.get(position).getArea()));
        holder.ada.setText(String.valueOf(gardensList.get(position).getAda()));
        holder.parsel.setText(String.valueOf(gardensList.get(position).getParsel()));
        holder.city.setText(gardensList.get(position).getCity());
        holder.town.setText(gardensList.get(position).getTown());
        holder.district.setText(gardensList.get(position).getDistrict());


    }

    @Override
    public int getItemCount() {
        if (gardensList != null) return gardensList.size();
        else return 0;
    }


    public void addList(List<Gardens> list_data) {
        for (Gardens g : list_data) {
            gardensList.add(g);
        }
        notifyDataSetChanged();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView gardenName, area, ada, parsel, city, town, district;
        private CardView cardView;
        private ImageView editGarden, deletegarden;


        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public myViewHolder(@NonNull View itemView, final List<Gardens> costsList) {
            super(itemView);
            cardView = itemView.findViewById(R.id.gardencardview);
            gardenName = (TextView) itemView.findViewById(R.id.tarlaAdıInfo);
            area = (TextView) itemView.findViewById(R.id.tarlaalnınfo);
            ada = (TextView) itemView.findViewById(R.id.adaınfo);
            parsel = (TextView) itemView.findViewById(R.id.parselınfo);
            city = (TextView) itemView.findViewById(R.id.cityınfo);
            town = (TextView) itemView.findViewById(R.id.towninfo);
            district = (TextView) itemView.findViewById(R.id.districtinfo);
            editGarden = itemView.findViewById(R.id.editgarden1);
            deletegarden = itemView.findViewById(R.id.deletegarden);

            editGarden.setOnClickListener(v -> {
                Intent ıntent = new Intent(context, EditGarden.class);
                ıntent.putExtra("userID", User_Login.userID);
                ıntent.putExtra("gardenID", gardensList.get(getAdapterPosition()).getGardenID());
                v.getContext().startActivity(ıntent);
            });

            deletegarden.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ıntent = new Intent(context, deletegarden_pop.class);
                    ıntent.putExtra("userID", User_Login.userID);
                    ıntent.putExtra("gardenID", gardensList.get(getAdapterPosition()).getGardenID());
                    v.getContext().startActivity(ıntent);
                  // v.getContext().startActivity(new Intent(v.getContext(), deletegarden_pop.class));
                }

            });
        }


    }


}