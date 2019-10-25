package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sajidur.diugo.Backend.Computers;
import com.sajidur.diugo.Backend.DataHold;
import com.sajidur.diugo.Backend.RecyclerViewAdapterComputers;
import com.sajidur.diugo.Backend.RecyclerViewAdapterStudySpace;
import com.sajidur.diugo.Backend.RecyclerViewAdapterStudySpaceSeat;
import com.sajidur.diugo.Backend.StudySpaceSeat;

import java.util.ArrayList;

public class StudySpaceDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_space_data);

        ArrayList<StudySpaceSeat> studySpaceSeatArrayList = DataHold.studySpaceSeatArrayList;

        if(studySpaceSeatArrayList==null){
            new MaterialAlertDialogBuilder(StudySpaceDataActivity.this)
                    .setTitle("Confirm")
                    .setMessage("Data Not Received From Server,Please Try Again")
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(StudySpaceDataActivity.this,Dashboard.class));
                            StudySpaceDataActivity.this.finish();
                        }
                    })
                    .show();
        }else if(studySpaceSeatArrayList.size()<1){

            new MaterialAlertDialogBuilder(StudySpaceDataActivity.this)
                    .setTitle("Confirm")
                    .setMessage("Data Not Received From Server,Please Try Again")
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(StudySpaceDataActivity.this,Dashboard.class));
                            StudySpaceDataActivity.this.finish();
                        }
                    })
                    .show();
        }
        final RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        final RecyclerViewAdapterStudySpaceSeat recyclerViewAdapterStudySpaceSeat= new RecyclerViewAdapterStudySpaceSeat(this,studySpaceSeatArrayList);
        recyclerView.setAdapter(recyclerViewAdapterStudySpaceSeat);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
    }
}
