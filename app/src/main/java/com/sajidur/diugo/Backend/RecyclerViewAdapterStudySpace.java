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
import com.sajidur.diugo.StudySpaceDataGetActivity;

import java.util.ArrayList;

public class RecyclerViewAdapterStudySpace extends RecyclerView.Adapter<RecyclerViewAdapterStudySpace.ViewHolder> {


    private Context mcontext;
    private ArrayList<StudySpace> studySpaceArrayList;
    private RecyclerViewAdapterLabs.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(RecyclerViewAdapterLabs.OnItemClickListener listener){
        mListener=listener;
    }

    public RecyclerViewAdapterStudySpace(Context context, ArrayList<StudySpace> studySpaceArrayList ){
        mcontext=context;
        this.studySpaceArrayList=studySpaceArrayList;
    }


    @NonNull
    @Override
    public RecyclerViewAdapterStudySpace.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studyspacecardview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterStudySpace.ViewHolder holder, int position) {

        if(studySpaceArrayList!=null){
            //Users users =usersArrayList.get(position);
            holder.textView.setText(studySpaceArrayList.get(position).getName());

        }

    }

    @Override
    public int getItemCount() {
        if(studySpaceArrayList==null){

            return  0;
        }else {
            return studySpaceArrayList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.studyspacename);
            imageView=(ImageView)itemView.findViewById(R.id.studyspaceImage);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();

                    DataHold.StudySpaceNo=studySpaceArrayList.get(position).getID();
                    mcontext.startActivity(new Intent( mcontext, StudySpaceDataGetActivity.class));
                }
            });



        }
    }


}