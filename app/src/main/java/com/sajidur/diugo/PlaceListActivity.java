package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PlaceListActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        listView=(ListView)findViewById(R.id.placelist);

        final String[] placeName= getResources().getStringArray(R.array.placeList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlaceListActivity.this,R.layout.listitemcampuscontents,R.id.texViewID,placeName);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String location=placeName[i];
                //Toast.makeText(PlaceListActivity.this,location,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(PlaceListActivity.this,PlaceFinderActivity.class);
                intent.putExtra("location",location);
                startActivity(intent);
            }
        });
    }
}
