package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.card.MaterialCardView;

public class Dashboard extends AppCompatActivity {
    MaterialCardView news,training,elearn,library,weblink,researchlab,studySpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        news=findViewById(R.id.diunews);
        training=findViewById(R.id.view4);
        elearn=findViewById(R.id.view6);
        library=findViewById(R.id.view5);
        weblink=findViewById(R.id.view3);
        researchlab=(MaterialCardView) findViewById(R.id.viewResearchLab);


       weblink.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(Dashboard.this,WebLinks.class);
               startActivity(intent);
           }
       });

       news.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String diunews="https://news.daffodilvarsity.edu.bd/";
               Intent intent = new Intent(Dashboard.this,ViewWebPages.class);
               intent.putExtra("News",diunews);
               startActivity(intent);
           }
       });

       training.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String skilltraining="http://training.skill.jobs/";
               Intent intent = new Intent(Dashboard.this,ViewWebPages.class);
               intent.putExtra("News",skilltraining);
               startActivity(intent);
           }
       });

       elearn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String diublended="https://elearn.daffodil.university/";
               Intent intent = new Intent(Dashboard.this,ViewWebPages.class);
               intent.putExtra("News",diublended);
               startActivity(intent);
           }
       });

       library.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String diulibrary="http://library.daffodilvarsity.edu.bd/";
               Intent intent = new Intent(Dashboard.this,ViewWebPages.class);
               intent.putExtra("News",diulibrary);
               startActivity(intent);
           }
       });

       researchlab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(Dashboard.this,ResearchLabActivity.class));
           }
       });

    }


}
