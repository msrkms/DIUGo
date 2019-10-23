package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AdministrativeOfficialsActivity extends AppCompatActivity {
TextView topmanagementtxt,directortxt,jointdirectortxt,deputyregistertxt,seniorassistantregistertxt,assistantregistertxt,seniorofficertxt,officertxt,assistantofficertxt,officeassistanttxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrative_officials);

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

    }
}
