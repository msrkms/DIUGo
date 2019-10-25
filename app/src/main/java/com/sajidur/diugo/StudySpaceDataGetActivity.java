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
import com.sajidur.diugo.Backend.StudySpace;
import com.sajidur.diugo.Backend.StudySpaceSeat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StudySpaceDataGetActivity extends AppCompatActivity {

    ArrayList<StudySpaceSeat> studySpaceSeatArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_space_data_get);
        new getData().execute();
    }


    class getData extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... voids) {
            getVolley();
            return null;
        }



        private void getVolley(){

            String URLline = MyUrl.getStudySpaceSeatPart1 + DataHold.StudySpaceNo+MyUrl.getStudySpaceSeatPart2;

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
            RequestQueue requestQueue = Volley.newRequestQueue(StudySpaceDataGetActivity.this);

            requestQueue.add(stringRequest);
        }

        public void parseData(String response) {

            try {

                JSONArray dataArray = new JSONArray(response);
                studySpaceSeatArrayList =new ArrayList<StudySpaceSeat>();
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    StudySpaceSeat studySpaceSeat=new StudySpaceSeat();
                    studySpaceSeat.setID(Integer.parseInt( dataobj.getString("id")));
                    studySpaceSeat.setAvailable( Boolean.parseBoolean( dataobj.getString("isAvailable")));
                    studySpaceSeatArrayList.add(studySpaceSeat);
                }
                if(!(DataHold.studySpaceSeatArrayList ==null)) {

                    DataHold.studySpaceSeatArrayList.clear();
                }
                DataHold.studySpaceSeatArrayList=studySpaceSeatArrayList;

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onPostExecute(String s) {
            DataHold.DataGetsFor=DataHold.StudySpaceSeat;
            Intent intent= new Intent(StudySpaceDataGetActivity.this,DataLoadingActivity.class);
            startActivity(intent);
            StudySpaceDataGetActivity.this.finish();
        }
    }
}
