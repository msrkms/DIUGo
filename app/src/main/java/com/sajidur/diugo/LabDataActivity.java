package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sajidur.diugo.Backend.Computers;
import com.sajidur.diugo.Backend.DataHold;
import com.sajidur.diugo.Backend.RecyclerViewAdapterComputers;

import java.util.ArrayList;

public class LabDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_data);

        ArrayList<Computers> computersArrayList = DataHold.computersArrayList;

        if(computersArrayList==null){
            new MaterialAlertDialogBuilder(LabDataActivity.this)
                    .setTitle("Confirm")
                    .setMessage("Data Not Received From Server,Please Try Again")
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            LabDataActivity.this.finish();
                        }
                    })
                    .show();
        }else if(computersArrayList.size()<1){
            new MaterialAlertDialogBuilder(LabDataActivity.this)
                    .setTitle("Confirm")
                    .setMessage("Data Not Received From Server,Please Try Again")
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            LabDataActivity.this.finish();
                        }
                    })
                    .show();
        }
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerViewAdapterComputers recyclerViewAdapter= new RecyclerViewAdapterComputers(this,computersArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
    }
}
