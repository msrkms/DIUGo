package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

public class MapViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        ZoomageView zoomageView = (ZoomageView)findViewById(R.id.myZoomageView);

        String cname = getIntent().getStringExtra("cname");
        if(cname.equals("Main Campus")){

            Toast.makeText(MapViewActivity.this,"Map has not uploaded yet",Toast.LENGTH_LONG).show();


        }
        else if(cname.equals("Permanent Campus")){
            String imagelink = getString(R.string.permanentCampusImage);
            Toast.makeText(MapViewActivity.this,"PermanentCampus",Toast.LENGTH_LONG).show();
            Picasso.get().load(imagelink).into(zoomageView);
        }
        else if(cname.equals("Uttara Campus")){
            Toast.makeText(MapViewActivity.this,"Map has not uploaded yet",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(MapViewActivity.this,"Not Matched",Toast.LENGTH_LONG).show();
        }
    }
}
