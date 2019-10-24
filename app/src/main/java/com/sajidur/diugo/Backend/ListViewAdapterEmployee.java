package com.sajidur.diugo.Backend;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;
import com.sajidur.diugo.MapViewActivity;
import com.sajidur.diugo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListViewAdapterEmployee extends BaseAdapter {

    private Context context;
    private ArrayList<Employee> dataModelArrayList;

    public ListViewAdapterEmployee(Context context, ArrayList<Employee> dataModelArrayList) {

        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewAdapterEmployee.ViewHolder holder;

        if (convertView == null) {
            holder = new  ListViewAdapterEmployee.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.administrativeinformationlayout, null, true);


            holder.imageViewProfile=(ImageView) convertView.findViewById(R.id.profileimge);
            holder.textViewName=(TextView) convertView.findViewById(R.id.name);
            holder.textViewDesignation=(TextView) convertView.findViewById(R.id.designation);
            holder.textViewPhone=(TextView) convertView.findViewById(R.id.phone);
            holder.textViewEmail=(TextView) convertView.findViewById(R.id.email);
            holder.textViewCampus=(TextView) convertView.findViewById(R.id.campus);





            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ListViewAdapterEmployee.ViewHolder)convertView.getTag();
        }

        String imagelink = dataModelArrayList.get(position).getImage();
        //Toast.makeText(MapViewActivity.this,"PermanentCampus",Toast.LENGTH_LONG).show();
        Picasso.get().load(imagelink).into(holder.imageViewProfile);
       // holder.imageViewProfile.setImageBitmap(dataModelArrayList.get(position).getBmp());
        holder.textViewName.setText(dataModelArrayList.get(position).getName());
        holder.textViewDesignation.setText(dataModelArrayList.get(position).getDesignation());
        holder.textViewPhone.setText(dataModelArrayList.get(position).getPhone());
        System.out.println("E"+dataModelArrayList.get(position).getEmail());
        holder.textViewEmail.setText(dataModelArrayList.get(position).getEmail());
        holder.textViewCampus.setText(dataModelArrayList.get(position).getCampus());


        return convertView;
    }

    private class ViewHolder {

        protected TextView textViewName,textViewDesignation,textViewPhone,textViewEmail,textViewCampus;
        protected ImageView imageViewProfile;

    }

}
