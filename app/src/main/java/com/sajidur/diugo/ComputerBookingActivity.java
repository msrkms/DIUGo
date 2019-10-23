package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.sajidur.diugo.Backend.ComputerBookingInfo;
import com.sajidur.diugo.Backend.Computers;
import com.sajidur.diugo.Backend.DataHold;
import com.sajidur.diugo.Backend.DateFormatting;
import com.sajidur.diugo.Backend.ListViewAdapterComputerBookingInfo;
import com.sajidur.diugo.Backend.MyUrl;
import com.sajidur.diugo.Backend.RecyclerViewAdapterComputerBookingInfo;
import com.sajidur.diugo.Backend.RecyclerViewAdapterLabs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ComputerBookingActivity extends AppCompatActivity {

    ListView listViewBooking;
    LinearLayout linearLayoutBookingDetailsHeader;
    private final  int StartTimeField=1,EndTimeField=2;
    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;
    private Calendar calendar;
    private MaterialTextView materialTextViewBookingDetails,materialTextViewDate,materialTextViewStartTime,getMaterialTextViewEndTime;
    private MaterialButton materialButtonBook;
    private int year, month, day,hour,minute;
    private ProgressDialog progressDialog ;
    private ArrayList<ComputerBookingInfo>  computerBookingInfosArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_booking);




        materialTextViewBookingDetails=(MaterialTextView) findViewById(R.id.materialTextViewBookingDetails);
        materialTextViewDate = (MaterialTextView) findViewById(R.id.materialTextViewDate);
        materialTextViewStartTime=(MaterialTextView) findViewById(R.id.materialTextViewBookingStartTime);
        getMaterialTextViewEndTime=(MaterialTextView) findViewById(R.id.materialTextViewBookingEndTime);
        materialButtonBook=(MaterialButton) findViewById(R.id.materialButtonBookComputer);
        listViewBooking=(ListView) findViewById(R.id.listViewBookingDetails);
        materialTextViewBookingDetails.setVisibility(View.GONE);
        linearLayoutBookingDetailsHeader=(LinearLayout)findViewById(R.id.dataheader);
        linearLayoutBookingDetailsHeader.setVisibility(View.GONE);
        materialTextViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);


                 datePicker = new DatePickerDialog(ComputerBookingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String Date=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                DataHold.Date=Date;
                                materialTextViewDate.setText(Date);
                                showGetData();
                              //  new getData().execute();
                                getBookingInfo();

                            }
                        }, year, month, day);
                datePicker.show();


            }
        });

        materialTextViewStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime(StartTimeField);
            }
        });

        getMaterialTextViewEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime(EndTimeField);
            }
        });



        materialButtonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookComputer();
            }
        });

    }



    private void bookComputer(){



        DateFormatting dateFormatting=new DateFormatting();
        final String Date=dateFormatting.dateConvertforPost(materialTextViewDate.getText().toString());
        final String StartTime=dateFormatting.timeConvertforPost(materialTextViewStartTime.getText().toString());
        final String EndTime=dateFormatting.timeConvertforPost(getMaterialTextViewEndTime.getText().toString());
        final String User =DataHold.User;
        final String Computer=String.valueOf(DataHold.ComputerID);



        JSONObject postparams = new JSONObject();
        try {

            postparams.put("bookingDate",Date);
            postparams.put("bookingStartTime",StartTime);
            postparams.put("bookingEndTime",EndTime);
            postparams.put("c_ID",Computer);
            postparams.put("u_ID",User);

            System.out.println("Arra"+postparams);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                MyUrl.BookComputer, postparams,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        try{
                            JSONObject jsonObject= new JSONObject(response.toString());
                            if(!(jsonObject==null)){
                                showGetData();
                                getBookingInfo();
                                Toast.makeText(ComputerBookingActivity.this,"Your Booking Request Accepted,If you late 20 min your booking will no longer valid ",Toast.LENGTH_LONG).show();

                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });





        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private void showGetData(){
        this.progressDialog= new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setTitle("Getting Data");
        progressDialog.show();

    }

    public void setTime(final int setTimeFor){
        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
         timePicker = new TimePickerDialog(ComputerBookingActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String Min=String.valueOf(minute);
                        if(minute<10){
                            Min="0"+minute;
                        }

                        String Hour=String.valueOf(hourOfDay);
                        if(hourOfDay<10){
                            Hour="0"+hourOfDay;
                        }

                        if(setTimeFor==StartTimeField){
                            materialTextViewStartTime.setText(Hour + ":" + Min);
                        }else if(setTimeFor==EndTimeField){
                            getMaterialTextViewEndTime.setText(Hour + ":" + Min);
                        }

                    }
                }, hour, minute, false);
        timePicker.show();
    }





    private void getBookingInfo() {

        DataHold.Date=materialTextViewDate.getText().toString();

        String URLline = MyUrl.GetComputerBookingInfoPart1 + DataHold.ComputerID+MyUrl.getGetComputerBookingInfoPart2+DataHold.Date;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLline,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray dataArray = new JSONArray(response);
                            computerBookingInfosArrayList =new ArrayList<ComputerBookingInfo>();
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                ComputerBookingInfo computersBookingInfo=new ComputerBookingInfo();
                                computersBookingInfo.setID(Integer.parseInt( dataobj.getString("id")));
                                computersBookingInfo.setBookingDate( ( dataobj.getString("bookingDate")));
                                computersBookingInfo.setBookingStartTime(dataobj.getString("bookingStartTime"));
                                computersBookingInfo.setBookingEndTime(dataobj.getString("bookingEndTime"));


                                System.out.println(computersBookingInfo.getBookingDate());
                                DateFormatting dateFormatting= new DateFormatting();
                                computersBookingInfo.setBookingDate(dateFormatting.dateConvert(computersBookingInfo.getBookingDate()));
                                System.out.println(computersBookingInfo.getBookingDate());
                                computersBookingInfo.setBookingStartTime(dateFormatting.timeConvert(computersBookingInfo.getBookingStartTime()));
                                computersBookingInfo.setBookingEndTime(dateFormatting.timeConvert(computersBookingInfo.getBookingEndTime()));

                                computersBookingInfo.setC_ID(dataobj.getInt("c_ID"));
                                computersBookingInfo.setU_ID(dataobj.getString("u_ID"));
                                computerBookingInfosArrayList.add(computersBookingInfo);
                            }
                            if(!(DataHold.computerBookingInfosArrayList ==null)) {
                                DataHold.computerBookingInfosArrayList.clear();
                            }
                            DataHold.computerBookingInfosArrayList=computerBookingInfosArrayList;

                            if(!(computerBookingInfosArrayList==null) && computerBookingInfosArrayList.size()>0){
                                ListViewAdapterComputerBookingInfo adapterComputerBookingInfo= new ListViewAdapterComputerBookingInfo(ComputerBookingActivity.this,DataHold.computerBookingInfosArrayList);
                                materialTextViewBookingDetails.setVisibility(View.VISIBLE);
                                linearLayoutBookingDetailsHeader.setVisibility(View.VISIBLE);
                                listViewBooking.setAdapter(adapterComputerBookingInfo);
                            }else{
                                listViewBooking.setAdapter(null);
                                materialTextViewBookingDetails.setVisibility(View.GONE);
                                linearLayoutBookingDetailsHeader.setVisibility(View.GONE);
                            }
                            progressDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            listViewBooking.setAdapter(null);
                            materialTextViewBookingDetails.setVisibility(View.GONE);
                            linearLayoutBookingDetailsHeader.setVisibility(View.GONE);
                            Toast.makeText(ComputerBookingActivity.this,"Fail to get data",Toast.LENGTH_SHORT);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }

}

