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
import com.google.android.material.textview.MaterialTextView;
import com.sajidur.diugo.ComputerBookingActivity;
import com.sajidur.diugo.LabDataActivity;
import com.sajidur.diugo.LogIn;
import com.sajidur.diugo.R;

import java.util.ArrayList;

public class RecyclerViewAdapterComputerBookingInfo extends RecyclerView.Adapter<RecyclerViewAdapterComputerBookingInfo.ViewHolder>{

    private ArrayList<ComputerBookingInfo> computerBookingInfoArrayList;
    private Context mContext;

    public RecyclerViewAdapterComputerBookingInfo(Context context, ArrayList<ComputerBookingInfo> computerBookingInfoArrayList ) {
        mContext = context;
        this.computerBookingInfoArrayList=computerBookingInfoArrayList;
    }

    @Override
    public RecyclerViewAdapterComputerBookingInfo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.computerbookinginfoview, parent, false);
        RecyclerViewAdapterComputerBookingInfo.ViewHolder holder = new RecyclerViewAdapterComputerBookingInfo.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterComputerBookingInfo.ViewHolder holder, final int position) {

        holder.materialTextViewDate.setText(computerBookingInfoArrayList.get(position).getBookingDate());
        holder.materialTextViewStartTime.setText(computerBookingInfoArrayList.get(position).getBookingStartTime());
        holder.materialTextViewEndTime.setText(computerBookingInfoArrayList.get(position).getBookingEndTime());
        holder.materialTextViewUser.setText(computerBookingInfoArrayList.get(position).getU_ID());

    }

    @Override
    public int getItemCount() {
        if(computerBookingInfoArrayList==null){

            return  0;
        }else {
            return computerBookingInfoArrayList.size();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        MaterialTextView materialTextViewDate,materialTextViewStartTime,materialTextViewEndTime,materialTextViewUser;
        public ViewHolder(View itemView) {
            super(itemView);

            materialTextViewDate=(MaterialTextView) itemView.findViewById(R.id.BookingDate);
            materialTextViewEndTime=(MaterialTextView) itemView.findViewById(R.id.EndTime);
            materialTextViewStartTime=(MaterialTextView) itemView.findViewById(R.id.StartTime);
            materialTextViewUser=(MaterialTextView) itemView.findViewById(R.id.User);



        }
    }
}