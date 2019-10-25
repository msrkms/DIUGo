package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sajidur.diugo.Backend.Computers;
import com.sajidur.diugo.Backend.DataHold;

import java.util.ArrayList;

public class DataLoadingActivity extends AppCompatActivity {


    private static int SPLASH_TIME = 3500;
    ArrayList<Computers> computersArrayList =new ArrayList<Computers>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_loading);

        new Handler().postDelayed(new Runnable() {

            public void run() {

                if(DataHold.DataGetsFor==DataHold.Labs){
                    Intent mySuperIntent = new Intent(DataLoadingActivity.this, ResearchLabActivity.class);
                    startActivity(mySuperIntent);
                    DataLoadingActivity.this.finish();
                }else if(DataHold.DataGetsFor==DataHold.Computers){
                    Intent mySuperIntent = new Intent(DataLoadingActivity.this, LabDataActivity.class);
                    startActivity(mySuperIntent);
                    DataLoadingActivity.this.finish();
                }else if(DataHold.DataGetsFor==DataHold.StudySpace){
                    Intent mySuperIntent = new Intent(DataLoadingActivity.this, StudySpaceActivity.class);
                    startActivity(mySuperIntent);
                    DataLoadingActivity.this.finish();
                }else if(DataHold.DataGetsFor==DataHold.StudySpaceSeat){
                    Intent mySuperIntent = new Intent(DataLoadingActivity.this, StudySpaceDataActivity.class);
                    startActivity(mySuperIntent);
                    DataLoadingActivity.this.finish();
                }
            }
        }, SPLASH_TIME);
    }
}
