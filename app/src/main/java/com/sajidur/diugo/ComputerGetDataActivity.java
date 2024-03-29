package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sajidur.diugo.Backend.Computers;
import com.sajidur.diugo.Backend.DataHold;
import com.sajidur.diugo.Backend.MyUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ComputerGetDataActivity extends AppCompatActivity {

    ArrayList<Computers> computersArrayList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_get_data);
        new getData().execute();
    }

    class getData extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... voids) {
            getVolley();
            return null;
        }



        private void getVolley(){

            String URLline = MyUrl.getComputersPart1 + DataHold.LabNo+MyUrl.getComputersPart2;
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
            RequestQueue requestQueue = Volley.newRequestQueue(ComputerGetDataActivity.this);

            requestQueue.add(stringRequest);
        }

        public void parseData(String response) {

            try {

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
            DataHold.DataGetsFor=DataHold.Computers;
            Intent intent= new Intent(ComputerGetDataActivity.this,DataLoadingActivity.class);
            startActivity(intent);
            ComputerGetDataActivity.this.finish();
        }
    }
}
