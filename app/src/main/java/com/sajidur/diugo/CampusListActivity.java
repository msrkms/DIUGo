package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CampusListActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_list);

        listView=(ListView)findViewById(R.id.campuslist);

        final String[] campusName = getResources().getStringArray(R.array.campuses);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CampusListActivity.this,R.layout.listitemcampuscontents,R.id.texViewID,campusName);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = campusName[i];
                //Toast.makeText(MainActivity.this,name, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CampusListActivity.this,MapViewActivity.class);
                intent.putExtra("cname",name);
                startActivity(intent);
            }
        });
    }
}
