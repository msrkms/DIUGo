package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sajidur.diugo.Backend.DataHold;
import com.sajidur.diugo.Backend.RecyclerViewAdapterLabs;
import com.sajidur.diugo.Backend.RecyclerViewAdapterStudySpace;
import com.sajidur.diugo.Backend.StudySpace;

import java.util.ArrayList;

public class StudySpaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_space);


        ArrayList<StudySpace> studySpaceArrayList= DataHold.studySpaceArrayList;

        if(studySpaceArrayList==null){

            new MaterialAlertDialogBuilder(StudySpaceActivity.this)
                    .setTitle("Confirm").setMessage("Data Not Received Please Try Again")
                    .setPositiveButton("TryAgain", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            StudySpaceActivity.this.finish();
                        }
                    }).show();

        }
        else if(studySpaceArrayList.size()<1){

            new MaterialAlertDialogBuilder(StudySpaceActivity.this)
                    .setTitle("Confirm").setMessage("Data Not Received Please Try Again")
                    .setPositiveButton("TryAgain", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            StudySpaceActivity.this.finish();
                        }
                    }).show();

        }


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);
        RecyclerViewAdapterStudySpace recyclerViewAdapterStudySpace= new RecyclerViewAdapterStudySpace(this,studySpaceArrayList);
        recyclerView.setAdapter(recyclerViewAdapterStudySpace);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
    }
}
