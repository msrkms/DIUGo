package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.card.MaterialCardView;
import com.sajidur.diugo.Backend.Computers;
import com.sajidur.diugo.Backend.DataHold;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.sajidur.diugo.Backend.DataHold.computersArrayList;

public class ResearchLabActivity extends AppCompatActivity {

    ArrayList<Computers> computersArrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_lab);

        MaterialCardView cardViewLabA= (MaterialCardView) findViewById(R.id.viewLabA);
        cardViewLabA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataHold.LabNo=1;
                new getData().execute();
            }
        });
    }


    class getData extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... voids) {
            getVolley();
            return null;
        }



        private void getVolley(){

            String URLline = "http://msrkmstest-001-site1.gtempurl.com/api/Lab/"+DataHold.LabNo+"/Computer";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URLline,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            parseData(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //displaying the error in toast if occurrs

                        }
                    });

            // request queue
            RequestQueue requestQueue = Volley.newRequestQueue(ResearchLabActivity.this);

            requestQueue.add(stringRequest);
        }

        public void parseData(String response) {

            try {
                //  JSONObject jsonObject = new JSONObject(response);
                JSONArray dataArray = new JSONArray(response);
                computersArrayList =new ArrayList<Computers>();
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    Computers computers=new Computers();
                    computers.setID(Integer.parseInt( dataobj.getString("id")));
                    computers.setAvailable( Boolean.parseBoolean( dataobj.getString("isAvailable")));
                    computersArrayList.add(computers);
                }
                if(!(DataHold.computersArrayList ==null)) {

                    DataHold.computersArrayList.clear();
                }
                DataHold.computersArrayList=computersArrayList;

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onPostExecute(String s) {
            Intent intent= new Intent(ResearchLabActivity.this,DataLoadingActivity.class);
            startActivity(intent);
        }
    }
}
