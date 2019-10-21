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
import android.widget.Toast;

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
import static com.sajidur.diugo.Backend.DataHold.labsArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class ResearchLabActivity extends AppCompatActivity implements RecyclerViewAdapterLabs.OnItemClickListener{

    ArrayList<Computers>computersArrayList=new ArrayList<Computers>();
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

        recyclerViewAdapter.setOnItemClickListener(ResearchLabActivity.this);

    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(getApplicationContext(),Dashboard.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        Labs labs=labsArrayList.get(position);
        String labname=labs.getName();
        String id = labs.getID();
        //Toast.makeText(ResearchLabActivity.this,id,Toast.LENGTH_LONG).show();
//       Intent intent=new Intent(ResearchLabActivity.this,DataLoadingActivity.class);
//        intent.putExtra("ID",id);
//        intent.putExtra("labname",labname);
//        startActivity(intent);
        new getPCData(id).execute();
    }

    class getPCData extends AsyncTask<Void,Void,String> {
        private  String id;
        public getPCData(String id) {
            this.id=id;
        }

        @Override
        protected String doInBackground(Void... voids) {
            getVolley(id);
            return null;
        }

        private void getVolley(String id){
            final String lid=id;
            //  String URLline="http://sajidur.com/BloodApp/getdonorlist.php";
            String URLline="http://msrkmstest-001-site1.gtempurl.com/Api/Computer";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URLline, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                    parseData(response,lid);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("error"+error.toString());
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(ResearchLabActivity.this);
            requestQueue.add(stringRequest);
        }

        public void parseData(String response,String lid){
            System.out.println("Response"+response);
            try{
                //JSONObject jsonObject=new JSONObject(response);
                //JSONArray dataArray = jsonObject.getJSONArray("Donor_Data");
                JSONArray dataArray = new JSONArray(response);

                for(int i=0;i<dataArray.length();i++){

                    JSONObject dataobj=dataArray.getJSONObject(i);
                    if(lid==dataobj.getString("l_ID")){
                        Computers computers = new Computers();
                        computers.setID(Integer.parseInt(dataobj.getString("id")));
                        computers.setAvailable(Boolean.parseBoolean(dataobj.getString("isAvailable")));
                        System.out.println("testuser"+computers.getID());
                        computersArrayList.add(computers);
                    }

                }
                if(!(DataHold.computersArrayList==null)){
                    DataHold.computersArrayList.clear();
                }
                DataHold.computersArrayList=computersArrayList;
                System.out.println("test"+computersArrayList.size());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        @Override
        protected void onPostExecute(String s) {
            Intent intent = new Intent(ResearchLabActivity.this,DataLoadingActivity.class);
            startActivity(intent);
        }
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
