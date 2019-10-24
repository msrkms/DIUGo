package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sajidur.diugo.Backend.ComputerBookingInfo;
import com.sajidur.diugo.Backend.DataHold;
import com.sajidur.diugo.Backend.DateFormatting;
import com.sajidur.diugo.Backend.Employee;
import com.sajidur.diugo.Backend.ListViewAdapterComputerBookingInfo;
import com.sajidur.diugo.Backend.ListViewAdapterEmployee;
import com.sajidur.diugo.Backend.MyUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.sajidur.diugo.Backend.DataHold.computerBookingInfosArrayList;

public class EmployeeDetailsActivity extends AppCompatActivity {


    ArrayList<Employee> employeeArrayList;
    ListView listViewEmployee;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        listViewEmployee=(ListView) findViewById(R.id.listViewEmployee);
        TextView textViewShowingDatafor=(TextView) findViewById(R.id.textViewShowingDataFor);
        textViewShowingDatafor.setText(DataHold.ActivityHeader);
        showGetData();
        getBookingInfo();
    }

    private void showGetData(){
        this.progressDialog= new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setTitle("Getting Data");
        progressDialog.show();

    }



    private void getBookingInfo() {

        //  DataHold.Date=materialTextViewDate.getText().toString();

        String URLline = MyUrl.GetEmployeeByCategoryPart1 + DataHold.DataGetsFor+MyUrl.GetEmployeeByCategoryPart2;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLline,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                         //   System.out.println(response);
                            JSONArray dataArray = new JSONArray(response);
                            employeeArrayList =new ArrayList<Employee>();
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                Employee employee   = new Employee();
                                employee.setName(dataobj.getString("name"));
                                employee.setDesignation(dataobj.getString("designation"));
                                employee.setPhone(dataobj.getString("phone"));
                                employee.setEmail(dataobj.getString("email"));
                                employee.setCampus(dataobj.getString("campus"));
                                employee.setImage(dataobj.getString("image"));

                                //System.out.println(employee.getName());
                             //  URL imageurl=new URL(employee.getImage());
                               // employee.setBmp(BitmapFactory.decodeStream(imageurl.openConnection().getInputStream()));




                                employeeArrayList.add(employee);
                            }
                            if(!(DataHold.employeeArrayList ==null)) {
                                DataHold.employeeArrayList.clear();
                            }
                            DataHold.employeeArrayList=employeeArrayList;

                            if(!(DataHold.employeeArrayList==null) && DataHold.employeeArrayList.size()>0){
                                ListViewAdapterEmployee listViewAdapterEmployee= new ListViewAdapterEmployee(EmployeeDetailsActivity.this,DataHold.employeeArrayList);
                                listViewEmployee.setAdapter(listViewAdapterEmployee);
                            }else{
                                listViewEmployee.setAdapter(null);
                            }
                            progressDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            listViewEmployee.setAdapter(null);
                            Toast.makeText(EmployeeDetailsActivity.this,"Fail to get data",Toast.LENGTH_SHORT);

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
