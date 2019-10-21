package com.sajidur.diugo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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
import com.sajidur.diugo.Backend.Labs;
import com.sajidur.diugo.Backend.RecyclerViewAdapterLabs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class ResearchLabActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_lab);

        ArrayList<Labs>labsArrayList= DataHold.labsArrayList;
        System.out.println("test"+DataHold.labsArrayList.size());


        if(labsArrayList==null){

            new AlertDialog.Builder(ResearchLabActivity.this).setTitle("Confirm").setMessage("Data Not Received Please Try Again").setPositiveButton("TryAgain", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ResearchLabActivity.this.finish();
                }
            }).show();

        }
        else if(labsArrayList.size()<1){

            new AlertDialog.Builder(ResearchLabActivity.this).setTitle("Confirm").setMessage("Data Not Received Please Try Again").setPositiveButton("TryAgain", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ResearchLabActivity.this.finish();
                }
            }).show();

        }


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);
        RecyclerViewAdapterLabs recyclerViewAdapter= new RecyclerViewAdapterLabs(this,labsArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));


    }


//    class getData extends AsyncTask<Void,Void,String> {
//
//        @Override
//        protected String doInBackground(Void... voids) {
//            getVolley();
//            return null;
//        }
//
//
//
//        private void getVolley(){
//
//            String URLline = "http://msrkmstest-001-site1.gtempurl.com/api/Computers";
//
//
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, URLline,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//
//                            parseData(response);
//
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            //displaying the error in toast if occurrs
//
//                        }
//                    });
//
//            // request queue
//            RequestQueue requestQueue = Volley.newRequestQueue(ResearchLabActivity.this);
//
//            requestQueue.add(stringRequest);
//        }
//
//        public void parseData(String response) {
//
//            try {
//                //  JSONObject jsonObject = new JSONObject(response);
//
//                JSONArray dataArray = new JSONArray(response);
//                for (int i = 0; i < dataArray.length(); i++) {
//                    JSONObject dataobj = dataArray.getJSONObject(i);
//                    Computers computers=new Computers();
//                    computers.setID(Integer.parseInt( dataobj.getString("c_ID")));
//                    computers.setAvailable( Boolean.parseBoolean( dataobj.getString("is_Available")));
//                    computersArrayList.add(computers);
//                }
//                if(!(DataHold.computersArrayList ==null)) {
//
//
//                    DataHold.computersArrayList.clear();
//                }
//                DataHold.computersArrayList=computersArrayList;
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            Intent intent= new Intent(ResearchLabActivity.this,DataLoadingActivity.class);
//            startActivity(intent);
//        }
//    }
}
