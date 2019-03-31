 package com.creativecodes.maheutsav2019;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


 /**
  * Created by anupamchugh on 09/02/16.
  */
 public class CustomAdapterblood extends ArrayAdapter<DataModelblood> implements View.OnClickListener{

     private ArrayList<DataModelblood> dataSet;
     Context mContext;
     int posi;
 //    int hex = 0x45E213;

     // View lookup cache
     private static class ViewHolder {
         TextView txtName;
         TextView txtcontact;
         TextView txtbloodgrp;
         int bustime,currnt_time;
         TextView txtVersion;
         RelativeLayout rl;
         ImageView bloodim,manicon;
         CardView cdv;
     }



     public CustomAdapterblood(ArrayList<DataModelblood> data, Context context) {
         super(context, R.layout.blood_list_adapter, data);
         this.dataSet = data;
         this.mContext=context;
     }


     @Override
     public void onClick(View v) {


         int position=(Integer) v.getTag();
         Object object= getItem(position);
         DataModelblood dataModel=(DataModelblood)object;



         switch (v.getId())
         {

 //            case R.id.imageViewclock:
 //                String t=getCurrentTime();
 //
 ////                Toast.makeText(mContext, t, Toast.LENGTH_LONG).show();
 //
 ////                Snackbar.make(v, "Release date "+ t +dataModel.getFeature(), Snackbar.LENGTH_LONG)
 ////                        .setAction("No action", null).show();
 //
 //                break;
 //

         }


     }

     private int lastPosition = -1;

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
         // Get the data item for this position
         DataModelblood dataModel = getItem(position);
         if(position==posi) {
             // set some color

         }
         // Check if an existing view is being reused, otherwise inflate the view
         ViewHolder viewHolder; // view lookup cache stored in tag

         final View result;

         if (convertView == null) {


             viewHolder = new ViewHolder();
             LayoutInflater inflater = LayoutInflater.from(getContext());
             convertView = inflater.inflate(R.layout.blood_list_adapter, parent, false);
             viewHolder.txtName = (TextView) convertView.findViewById(R.id.textViewnameblood);
             viewHolder.txtcontact = (TextView) convertView.findViewById(R.id.textViewcontact);
             viewHolder.txtbloodgrp = (TextView) convertView.findViewById(R.id.textViewbloodgrp);
 //            viewHolder.busidtxtv = (TextView) convertView.findViewById(R.id.busidtext);
             viewHolder.manicon = (ImageView) convertView.findViewById(R.id.ivmanicon);
 //            viewHolder.rl=(RelativeLayout)convertView.findViewById(R.id.rllv);
 //            viewHolder.cdv=(CardView)convertView.findViewById(R.id.card);
             viewHolder.bloodim=(ImageView)convertView.findViewById(R.id.imageViewblooddrop);
             result=convertView;

             convertView.setTag(viewHolder);
         } else {
             viewHolder = (ViewHolder) convertView.getTag();
             result=convertView;
         }

         Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
         result.startAnimation(animation);
         lastPosition = position;

         viewHolder.manicon.setImageResource(R.drawable.ic_location);
         viewHolder.bloodim.setImageResource(R.drawable.ic_notifications_black_24dp);
         viewHolder.txtcontact.setText(dataModel.getDcontact());
 //        viewHolder.txtType.setText(dataModel.getType());
 //        String time=dataModel.getVersion_number();
         DateFormat inFormat = new SimpleDateFormat( "HH:mm" );
         Date date = null;

 //
 //        String a=dataModel.getInt_time().toString();
 //        Toast.makeText(mContext, a, Toast.LENGTH_SHORT).show();
         viewHolder.txtName.setText(dataModel.getDname());
         viewHolder.txtbloodgrp.setText(dataModel.getDtype());
 //        viewHolder.info.setOnClickListener(this);
 //        viewHolder.info.setTag(position);
         // Return the completed view to render on screen
         return convertView;
     }

     public String getCurrentTime() {
         Calendar calendar = Calendar.getInstance();
         SimpleDateFormat mdformat = new SimpleDateFormat("HHmm");
         String strDate =  mdformat.format(calendar.getTime());
         return  strDate;
     }
 }
