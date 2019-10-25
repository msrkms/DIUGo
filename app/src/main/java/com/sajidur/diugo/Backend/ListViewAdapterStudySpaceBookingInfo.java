package com.sajidur.diugo.Backend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.android.material.textview.MaterialTextView;
import com.sajidur.diugo.R;

import java.util.ArrayList;

public class ListViewAdapterStudySpaceBookingInfo extends BaseAdapter {

    private Context context;
    private ArrayList<StudySpaceBookingInfo> dataModelArrayList;

    public ListViewAdapterStudySpaceBookingInfo(Context context, ArrayList<StudySpaceBookingInfo> dataModelArrayList) {

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
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.computerbookinginfoview, null, true);


            holder.materialTextViewDate=(MaterialTextView) convertView.findViewById(R.id.BookingDate);
            holder.materialTextViewStartTime=(MaterialTextView) convertView.findViewById(R.id.StartTime);
            holder.materialTextViewEndTime=(MaterialTextView) convertView.findViewById(R.id.EndTime);
            holder.materialTextViewUser=(MaterialTextView) convertView.findViewById(R.id.User);




            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.materialTextViewUser.setText(dataModelArrayList.get(position).getU_ID());
        holder.materialTextViewDate.setText(dataModelArrayList.get(position).getBookingDate());
        holder.materialTextViewStartTime.setText(dataModelArrayList.get(position).getBookingStartTime());
        holder.materialTextViewEndTime.setText(dataModelArrayList.get(position).getBookingEndTime());

        return convertView;
    }

    class ViewHolder {

        protected MaterialTextView  materialTextViewDate,materialTextViewStartTime,materialTextViewEndTime,materialTextViewUser;

    }

}