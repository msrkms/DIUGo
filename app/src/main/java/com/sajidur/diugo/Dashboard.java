package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.card.MaterialCardView;
import com.sajidur.diugo.Backend.Labs;
import com.sajidur.diugo.Backend.DataHold;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.sajidur.diugo.Backend.DataHold.labsArrayList;

public class Dashboard extends AppCompatActivity {

    ArrayList<Labs> labsArrayList=new ArrayList<Labs>();
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
              // startActivity(new Intent(Dashboard.this,ResearchLabActivity.class));
               new getData().execute();
           }
       });

    }

    class getData extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            getVolley();
            return null;
        }

        private void getVolley(){
            //  String URLline="http://sajidur.com/BloodApp/getdonorlist.php";
            String URLline="http://msrkmstest-001-site1.gtempurl.com/Api/Lab";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URLline, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                    parseData(response);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("error"+error.toString());
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(Dashboard.this);
            requestQueue.add(stringRequest);
        }

        public void parseData(String response){
            System.out.println("Response"+response);
            try{
                //JSONObject jsonObject=new JSONObject(response);
                //JSONArray dataArray = jsonObject.getJSONArray("Donor_Data");
                JSONArray dataArray = new JSONArray(response);

                for(int i=0;i<dataArray.length();i++){

                    JSONObject dataobj=dataArray.getJSONObject(i);
                    Labs labs = new Labs();
                    labs.setName(dataobj.getString("labName"));
                    labs.setID(dataobj.getString("id"));
                    System.out.println("testuser"+labs.getName());
                    labsArrayList.add(labs);
                }
                if(!(DataHold.labsArrayList==null)){
                    DataHold.labsArrayList.clear();
                }
                DataHold.labsArrayList=labsArrayList;
                System.out.println("test"+labsArrayList.size());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        @Override
        protected void onPostExecute(String s) {
            Intent intent = new Intent(Dashboard.this,LoadingActivity.class);
            startActivity(intent);
        }
    }



}
