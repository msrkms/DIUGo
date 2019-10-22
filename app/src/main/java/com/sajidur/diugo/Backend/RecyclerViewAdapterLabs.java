package com.sajidur.diugo.Backend;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sajidur.diugo.ComputerGetDataActivity;
import com.sajidur.diugo.R;

import java.util.ArrayList;

import static com.sajidur.diugo.Backend.DataHold.computersArrayList;

public class RecyclerViewAdapterLabs extends RecyclerView.Adapter<RecyclerViewAdapterLabs.ViewHolder> {


    private Context mcontext;
    private ArrayList<Labs>labsArrayList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public RecyclerViewAdapterLabs(Context context, ArrayList<Labs> labsArrayList){
        mcontext=context;
        this.labsArrayList=labsArrayList;
    }


    @NonNull
    @Override
    public RecyclerViewAdapterLabs.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.researchlabscardview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterLabs.ViewHolder holder, int position) {

        if(labsArrayList!=null){
            //Users users =usersArrayList.get(position);
            holder.textView.setText(labsArrayList.get(position).getName());

        }

    }

    @Override
    public int getItemCount() {
        if(labsArrayList==null){

            return  0;
        }else {
            return labsArrayList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.labname);
            imageView=(ImageView)itemView.findViewById(R.id.LabImage);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();

                    DataHold.LabNo=labsArrayList.get(position).getID();
                    mcontext.startActivity(new Intent( mcontext, ComputerGetDataActivity.class));
                }
            });



        }
    }


}
