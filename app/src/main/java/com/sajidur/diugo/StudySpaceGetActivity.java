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
import com.sajidur.diugo.Backend.DataHold;
import com.sajidur.diugo.Backend.Labs;
import com.sajidur.diugo.Backend.MyUrl;
import com.sajidur.diugo.Backend.StudySpace;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StudySpaceGetActivity extends AppCompatActivity {
    ArrayList<StudySpace> studySpaceArrayList=new ArrayList<StudySpace>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_space_get);
        new  getData().execute();
    }



    class getData extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... voids) {
            getVolley();
            return null;
        }

        private void getVolley(){
            String URLline= MyUrl.GetAllStudySpace;
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

                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(StudySpaceGetActivity.this);
            requestQueue.add(stringRequest);
        }

        public void parseData(String response){
            System.out.println("Response"+response);
            try{
                JSONArray dataArray = new JSONArray(response);

                for(int i=0;i<dataArray.length();i++){

                    JSONObject dataobj=dataArray.getJSONObject(i);
                    StudySpace studySpace = new StudySpace();
                    studySpace.setName(dataobj.getString("placeName"));
                    studySpace.setID(dataobj.getInt("id"));
                    studySpaceArrayList.add(studySpace);
                }
                if(!(DataHold.studySpaceArrayList==null)){
                    DataHold.studySpaceArrayList.clear();
                }
                DataHold.studySpaceArrayList=studySpaceArrayList;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        @Override
        protected void onPostExecute(String s) {
            DataHold.DataGetsFor=DataHold.StudySpace;
            Intent intent = new Intent(StudySpaceGetActivity.this,DataLoadingActivity.class);
            startActivity(intent);
            StudySpaceGetActivity.this.finish();
        }
    }
}
