package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textview.MaterialTextView;
import com.sajidur.diugo.Backend.ComputerBookingInfo;
import com.sajidur.diugo.Backend.DataHold;
import com.sajidur.diugo.Backend.DateFormatting;
import com.sajidur.diugo.Backend.Labs;
import com.sajidur.diugo.Backend.ListViewAdapterComputerBookingInfo;
import com.sajidur.diugo.Backend.ListViewAdapterStudySpaceBookingInfo;
import com.sajidur.diugo.Backend.MyUrl;
import com.sajidur.diugo.Backend.RecyclerViewAdapterLabs;
import com.sajidur.diugo.Backend.StudySpaceBookingInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class StudySpaceBookingActivity extends AppCompatActivity {
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
    private ArrayList<StudySpaceBookingInfo>  studySpaceBookingInfoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_space_booking);



        materialTextViewBookingDetails=(MaterialTextView) findViewById(R.id.materialTextViewBookingDetails);
        materialTextViewDate = (MaterialTextView) findViewById(R.id.materialTextViewDate);
        materialTextViewStartTime=(MaterialTextView) findViewById(R.id.materialTextViewBookingStartTime);
        getMaterialTextViewEndTime=(MaterialTextView) findViewById(R.id.materialTextViewBookingEndTime);
        materialButtonBook=(MaterialButton) findViewById(R.id.materialButtonBook);
        listViewBooking=(ListView) findViewById(R.id.listViewBooking);
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


                datePicker = new DatePickerDialog(StudySpaceBookingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String dayofmonth=String.valueOf(dayOfMonth);
                                if(dayOfMonth<10){
                                    dayofmonth="0"+dayOfMonth;
                                }

                                String month=String.valueOf(monthOfYear+1);

                                if((monthOfYear+1)<10){
                                    month="0"+monthOfYear;
                                }

                                String Date=dayofmonth + "-" + (month) + "-" + year;
                                DataHold.Date=Date;
                                materialTextViewDate.setText(DataHold.Date);
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
        timePicker = new TimePickerDialog(StudySpaceBookingActivity.this,
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



    private void bookComputer(){



        DateFormatting dateFormatting=new DateFormatting();
        final String Date=dateFormatting.dateConvertforPost(materialTextViewDate.getText().toString());
        final String StartTime=dateFormatting.timeConvertforPost(materialTextViewStartTime.getText().toString());
        final String EndTime=dateFormatting.timeConvertforPost(getMaterialTextViewEndTime.getText().toString());
        final String User =DataHold.User;
        final String StudySpace=String.valueOf(DataHold.StudySpaceSeatNo);



        JSONObject postparams = new JSONObject();
        try {

            postparams.put("bookingDate",Date);
            postparams.put("bookingStartTime",StartTime);
            postparams.put("bookingEndTime",EndTime);
            postparams.put("s_ID",StudySpace);
            postparams.put("u_ID",User);

            System.out.println("Arra"+postparams);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                MyUrl.BookStudySpace, postparams,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        try{
                            JSONObject jsonObject= new JSONObject(response.toString());
                            if(!(jsonObject==null)){
                                showGetData();
                                getBookingInfo();
                                Toast.makeText(StudySpaceBookingActivity.this,"Your Booking Request Accepted,If you late 20 min your booking will no longer valid ",Toast.LENGTH_LONG).show();

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





    private void getBookingInfo() {

          DataHold.Date=materialTextViewDate.getText().toString();

        String URLline = MyUrl.GetStudySpaceBookingInfoPart1 + DataHold.StudySpaceSeatNo+MyUrl.GetStudySpaceBookingInfoPart2+DataHold.Date;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLline,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray dataArray = new JSONArray(response);
                            studySpaceBookingInfoArrayList =new ArrayList<StudySpaceBookingInfo>();
                            studySpaceBookingInfoArrayList.clear();
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject dataobj = dataArray.getJSONObject(i);

                                StudySpaceBookingInfo studySpaceBookingInfo= new StudySpaceBookingInfo();

                                studySpaceBookingInfo.setID(Integer.parseInt( dataobj.getString("id")));
                                studySpaceBookingInfo.setBookingDate( ( dataobj.getString("bookingDate")));
                                studySpaceBookingInfo.setBookingStartTime(dataobj.getString("bookingStartTime"));
                                studySpaceBookingInfo.setBookingEndTime(dataobj.getString("bookingEndTime"));


                                System.out.println(studySpaceBookingInfo.getBookingDate());
                                DateFormatting dateFormatting= new DateFormatting();
                                studySpaceBookingInfo.setBookingDate(dateFormatting.dateConvert(studySpaceBookingInfo.getBookingDate()));
                                System.out.println(studySpaceBookingInfo.getBookingDate());
                                studySpaceBookingInfo.setBookingStartTime(dateFormatting.timeConvert(studySpaceBookingInfo.getBookingStartTime()));
                                studySpaceBookingInfo.setBookingEndTime(dateFormatting.timeConvert(studySpaceBookingInfo.getBookingEndTime()));

                                studySpaceBookingInfo.setS_ID(dataobj.getInt("s_ID"));
                                studySpaceBookingInfo.setU_ID(dataobj.getString("u_ID"));
                                studySpaceBookingInfoArrayList.add(studySpaceBookingInfo);
                            }
                            if(!(DataHold.studySpaceBookingInfoArrayList ==null)) {
                                DataHold.studySpaceSeatArrayList.clear();
                            }
                            DataHold.studySpaceBookingInfoArrayList=studySpaceBookingInfoArrayList;

                            if(!(studySpaceBookingInfoArrayList==null) && studySpaceBookingInfoArrayList.size()>0){
                                ListViewAdapterStudySpaceBookingInfo listViewAdapterStudySpaceBookingInfo= new ListViewAdapterStudySpaceBookingInfo(StudySpaceBookingActivity.this,DataHold.studySpaceBookingInfoArrayList);
                                materialTextViewBookingDetails.setVisibility(View.VISIBLE);
                                linearLayoutBookingDetailsHeader.setVisibility(View.VISIBLE);
                                listViewBooking.setAdapter(listViewAdapterStudySpaceBookingInfo);
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
                            Toast.makeText(StudySpaceBookingActivity.this,"Fail to get data",Toast.LENGTH_SHORT);

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
