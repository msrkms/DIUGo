package com.sajidur.diugo.Backend;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.sajidur.diugo.ComputerBookingActivity;
import com.sajidur.diugo.R;
import com.sajidur.diugo.StudySpaceBookingActivity;

import java.util.ArrayList;

public class RecyclerViewAdapterStudySpaceSeat extends RecyclerView.Adapter<RecyclerViewAdapterStudySpaceSeat.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<StudySpaceSeat> studySpaceSeatArrayList;
    private Context mContext;

    public RecyclerViewAdapterStudySpaceSeat(Context context, ArrayList<StudySpaceSeat> studySpaceSeatArrayList ) {
        mContext = context;
        this.studySpaceSeatArrayList=studySpaceSeatArrayList;
    }

    @Override
    public RecyclerViewAdapterStudySpaceSeat.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seat, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(studySpaceSeatArrayList.get(position).isAvailable()) {
            holder.imageView.setImageResource(R.drawable.seat_normal_available);
        }else{
            holder.imageView.setImageResource(R.drawable.seat_normal);
        }


    }

    @Override
    public int getItemCount() {
        if(studySpaceSeatArrayList==null){

            return  0;
        }else {
            return studySpaceSeatArrayList.size();
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

                    if(studySpaceSeatArrayList.get(position).isAvailable()){
                        DataHold.StudySpaceSeatNo=studySpaceSeatArrayList.get(position).getID();
                        mContext.startActivity(new Intent(mContext, StudySpaceBookingActivity.class));
                    }else{
                        Toast toast= Toast.makeText(mContext,"Not Available",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });

        }
    }
}