package com.example.costbook.adapter;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.costbook.R;
import com.example.costbook.pojo.Costs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class ProfileRecylerAdapter extends   RecyclerView.Adapter<ProfileRecylerAdapter.ViewHolder> {
    Calendar cal = Calendar.getInstance();
    String token;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Dialog errordialog ;
    ImageView closeError;
    String email,password;
    ProgressDialog pd;
    String userid;
    public Context context;
    public Button updateReview,deleteReview,scorePost,scoreDetailPost;
    private List<Costs> costList;
    Activity review;

    public ProfileRecylerAdapter(Context context, List<Costs> reviewList) {
        this.context=context;
        this.costList = reviewList;
    }
    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public ProfileRecylerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cost, parent, false);
        ViewHolder viewHolder = new ViewHolder(view,costList);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ProfileRecylerAdapter.ViewHolder holder, int position) {


//        holder.txtCostId.setText(Integer.valueOf(costList.get(position).getCostID()));
//        holder.txtCategoryID.setText( Integer.valueOf(costList.get(position).getCategoryID()));
//        holder.txtGardenID.setText(+Integer.valueOf(costList.get(position).getGardenID()));
//        holder.txtCost.setText(+Integer.valueOf(costList.get(position).getCost()));
//        Log.i("test", String.valueOf(Integer.valueOf(costList.get(0).getCostID())));

//        if(position == 0){
//
//            String curDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//            LocalDate date = LocalDate.parse(costList.get(position).getDate());
//            if(curDate.equals(date.toString())) holder.txtDate.setText("Bugün");
//            else holder.txtDate.setText(costList.get(position).getDate());
//        }
//
//        else if(position != 0 && !costList.get(position).getDate().equals(costList.get(position-1).getDate())){
//            cal.add(Calendar.DATE,-1);
//            if(costList.get(position).getDate().equals(format.format(cal.getTime()))){
//                holder.txtDate.setText("Dün");
//            }
//            else{
//                holder.txtDate.setText(costList.get(position).getDate());
//            }
//        }
//        else{
//            holder.txtDate.setVisibility(View.GONE);
//            holder.dateBackground.setVisibility(View.GONE);
//
//        }
    }
    @Override
    public int getItemCount() {
        if (costList != null) return costList.size();
        else return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtDate,txtCost,txtCostId,txtGardenID,
                txtCategoryID;
        ImageView imgRestaurant,dateBackground;
        ConstraintLayout expandableView ;
        Dialog reviewaction ;

        public ViewHolder(@NonNull View itemView, List<Costs> costsList) {
            super(itemView);








            itemView.setOnClickListener((View.OnClickListener) view -> {

                if(expandableView.getVisibility() == View.VISIBLE && reviewaction.isShowing() == false) {
                    expandableView.setVisibility(View.GONE);

                }
                else if(expandableView.getVisibility() == View.GONE && reviewaction.isShowing() == true){
                    reviewaction.dismiss();
                }
                else{
                    reviewaction.show();

                }






            });






        }

        private void initdialog(Dialog reviewaction) {
            System.out.println("Sistem hatası");
        }


    }



    public void addList(List<Costs> list_data) {
        if(list_data !=null) {

            for (Costs r : list_data) {
                Log.i("for","for");
                costList.add(r);


            }
            notifyDataSetChanged();

        }
            else
                Log.i("mesaj","bu null");
        }

        public void reverseList(){
        Log.i("ters","evet");
        Collections.reverse(costList);

        }




    public interface CustomCallback {
        void onSucess();
        void onFailure();
    }



    }




