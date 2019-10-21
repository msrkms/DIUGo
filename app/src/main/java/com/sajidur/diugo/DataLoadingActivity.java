package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sajidur.diugo.Backend.Computers;
import com.sajidur.diugo.Backend.DataHold;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataLoadingActivity extends AppCompatActivity {


    private static int SPLASH_TIME = 3500;
    ArrayList<Computers> computersArrayList =new ArrayList<Computers>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_loading);

       // String labID=getIntent().getStringExtra("ID");
        //String labname=getIntent().getStringExtra("labname");
        //System.out.println(labID);
        //Toast.makeText(DataLoadingActivity.this,labID+labname,Toast.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {

            public void run() {

                Intent mySuperIntent = new Intent(DataLoadingActivity.this, LabDataActivity.class);
                startActivity(mySuperIntent);
                DataLoadingActivity.this.finish();
            }
        }, 3500);
                //String labID=getIntent().getStringExtra("ID");
               // Toast.makeText(DataLoadingActivity.this,labID,Toast.LENGTH_LONG).show();
                //new getpcData(labID).execute();

    }

//    class getpcData extends AsyncTask<Void,Void,String> {
//        private  String id;
//        public getpcData(String id) {
//            this.id=id;
//        }
//
//        @Override
//        protected String doInBackground(Void... voids) {
//            getVolley(id);
//            return null;
//        }
//
//        private void getVolley(String id){
//            final String lid=id;
//            //  String URLline="http://sajidur.com/BloodApp/getdonorlist.php";
//            String URLline="http://msrkmstest-001-site1.gtempurl.com/Api/Computer";
//
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, URLline, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    System.out.println(response);
//                    parseData(response,lid);
//                }
//            },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            System.out.println("error"+error.toString());
//                        }
//                    });
//
//            RequestQueue requestQueue = Volley.newRequestQueue(DataLoadingActivity.this);
//            requestQueue.add(stringRequest);
//        }
//
//        public void parseData(String response,String lid){
//            System.out.println("Response"+response);
//            try{
//                //JSONObject jsonObject=new JSONObject(response);
//                //JSONArray dataArray = jsonObject.getJSONArray("Donor_Data");
//                JSONArray dataArray = new JSONArray(response);
//
//                for(int i=0;i<dataArray.length();i++){
//
//                    JSONObject dataobj=dataArray.getJSONObject(i);
//                        Computers computers = new Computers();
//                        computers.setID(Integer.parseInt(dataobj.getString("id")));
//                        computers.setAvailable(Boolean.parseBoolean(dataobj.getString("isAvailable")));
//                        System.out.println("testuser"+computers.getID());
//                        computersArrayList.add(computers);
//
//
//                }
//                if(!(DataHold.computersArrayList==null)){
//                    DataHold.computersArrayList.clear();
//                }
//                DataHold.computersArrayList=computersArrayList;
//                System.out.println("test"+computersArrayList.size());
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        @Override
//        protected void onPostExecute(String s) {
//            Intent intent = new Intent(DataLoadingActivity.this,LabDataActivity.class);
//            startActivity(intent);
//        }
//    }



}
