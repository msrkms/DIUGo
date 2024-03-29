package com.sajidur.diugo.Backend;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sajidur.diugo.ComputerBookingActivity;
import com.sajidur.diugo.LabDataActivity;
import com.sajidur.diugo.LogIn;
import com.sajidur.diugo.R;

import java.util.ArrayList;

public class RecyclerViewAdapterComputers extends RecyclerView.Adapter<RecyclerViewAdapterComputers.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Computers> computersArrayList;
    private Context mContext;

    public RecyclerViewAdapterComputers(Context context, ArrayList<Computers> computersArrayList ) {
        mContext = context;
        this.computersArrayList=computersArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seat, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(computersArrayList.get(position).isAvailable()) {
            holder.imageView.setImageResource(R.drawable.seat_normal_available);
        }else{
            holder.imageView.setImageResource(R.drawable.seat_normal);
        }


    }

    @Override
    public int getItemCount() {
        if(computersArrayList==null){

            return  0;
        }else {
            return computersArrayList.size();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

             imageView=(ImageView) itemView.findViewById(R.id.imageSeat);

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     int position=getAdapterPosition();

                     if(computersArrayList.get(position).isAvailable()){
                         DataHold.ComputerID=computersArrayList.get(position).getID();
                         mContext.startActivity(new Intent(mContext, ComputerBookingActivity.class));
                     }else{
                         Toast toast= Toast.makeText(mContext,"Not Available",Toast.LENGTH_SHORT);
                         toast.show();
                     }

                 }
             });

        }
    }
}