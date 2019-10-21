package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        new Handler().postDelayed(new Runnable() {

            public void run() {

                Intent mySuperIntent = new Intent(LoadingActivity.this, ResearchLabActivity.class);
                startActivity(mySuperIntent);
                LoadingActivity.this.finish();
            }
        }, 3500);
    }
}
