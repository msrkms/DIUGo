package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.sajidur.diugo.Backend.DataHold;

import org.w3c.dom.Text;

public class AdministrativeOfficialsActivity extends AppCompatActivity {
    MaterialCardView materialCardViewTopManagement,materialCardViewDirector;
TextView topmanagementtxt,directortxt,jointdirectortxt,deputyregistertxt,seniorassistantregistertxt,assistantregistertxt,seniorofficertxt,officertxt,assistantofficertxt,officeassistanttxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrative_officials);

        materialCardViewTopManagement=(MaterialCardView) findViewById(R.id.topmanagement);
        materialCardViewDirector=(MaterialCardView) findViewById(R.id.director);

        topmanagementtxt=(TextView)findViewById(R.id.managementtoptext);
        directortxt=(TextView)findViewById(R.id.directortext);
        jointdirectortxt=(TextView)findViewById(R.id.jointdirectortext);
        deputyregistertxt=(TextView)findViewById(R.id.deputyregistertext);
        seniorassistantregistertxt=(TextView)findViewById(R.id.seniorassistantregistertext);
        assistantregistertxt=(TextView)findViewById(R.id.assistantregistertext);
        seniorofficertxt=(TextView)findViewById(R.id.seniorofficertext);
        officertxt=(TextView)findViewById(R.id.officertext);
        assistantofficertxt=(TextView)findViewById(R.id.assistantofficertext);
        officeassistanttxt=(TextView)findViewById(R.id.officeassistanttext);

        topmanagementtxt.setSelected(true);
        directortxt.setSelected(true);
        jointdirectortxt.setSelected(true);
        deputyregistertxt.setSelected(true);
        seniorassistantregistertxt.setSelected(true);
        assistantregistertxt.setSelected(true);
        seniorofficertxt.setSelected(true);
        officertxt.setSelected(true);
        assistantofficertxt.setSelected(true);
        officeassistanttxt.setSelected(true);




        materialCardViewTopManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataHold.DataGetsFor=DataHold.TopLevelManagement;
                DataHold.ActivityHeader="Top Management";
                intentToEmployeeList();
            }
        });

        materialCardViewDirector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataHold.DataGetsFor=DataHold.Director;
                DataHold.ActivityHeader="Director";
                intentToEmployeeList();
            }
        });
    }

    private void  intentToEmployeeList(){
        startActivity(new Intent(AdministrativeOfficialsActivity.this,EmployeeDetailsActivity.class));

    }
}
