package com.sajidur.diugo.Backend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sajidur.diugo.R;

import java.util.ArrayList;

public class RecyclerViewAdapterLabs extends RecyclerView.Adapter<RecyclerViewAdapterLabs.ViewHolder> {


    private Context mcontext;
    private ArrayList<Labs>labsArrayList;

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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.labname);

        }
    }
}
