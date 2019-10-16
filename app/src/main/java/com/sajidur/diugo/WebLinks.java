package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class WebLinks extends AppCompatActivity {

    MaterialCardView studentaffair,carrerdevelopment,researchinformation,languageinstitutes,intituitionalassurance,hrdevelopment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_links);

        studentaffair = findViewById(R.id.studentaffairs);
        carrerdevelopment=findViewById(R.id.carrerdevelopment);
        researchinformation=findViewById(R.id.researchinfo);
        languageinstitutes=findViewById(R.id.languageinstitue);
        intituitionalassurance=findViewById(R.id.iqac);
        hrdevelopment=findViewById(R.id.humanresource);
        studentaffair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dsa="http://dsa.daffodilvarsity.edu.bd/";
                Intent intent= new Intent(WebLinks.this,ViewWebPages.class);
                intent.putExtra("News",dsa);
                startActivity(intent);
            }
        });


        carrerdevelopment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cdc= "http://cdc.daffodilvarsity.edu.bd/";
                Intent intent = new Intent(WebLinks.this,ViewWebPages.class);
                intent.putExtra("News",cdc);
                startActivity(intent);
            }
        });

        researchinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String research="http://research.daffodilvarsity.edu.bd/";
                Intent intent = new Intent(WebLinks.this,ViewWebPages.class);
                intent.putExtra("News",research);
                startActivity(intent);
            }
        });

        languageinstitutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dil="http://dil.daffodilvarsity.edu.bd/";
                Intent intent = new Intent(WebLinks.this,ViewWebPages.class);
                intent.putExtra("News",dil);
                startActivity(intent);
            }
        });

        intituitionalassurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String iqac="http://iqac.daffodilvarsity.edu.bd/";
                Intent intent = new Intent(WebLinks.this,ViewWebPages.class);
                intent.putExtra("News",iqac);
                startActivity(intent);
            }
        });

        hrdevelopment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hrdi="http://hrdinstitute.org/";
                Intent intent = new Intent(WebLinks.this,ViewWebPages.class);
                intent.putExtra("News",hrdi);
                startActivity(intent);
            }
        });

    }

}
