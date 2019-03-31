package com.creativecodes.maheutsav2019;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by anupamchugh on 09/02/16.
 */
public class CustomAdapterauto extends ArrayAdapter<DataModelauto> implements View.OnClickListener{

    private ArrayList<DataModelauto> dataSet;
    Context mContext;
    int posi;
//    int hex = 0x45E213;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtvenue;
        TextView txtorg;
        TextView txttime,txtdesc;
        int bustime,currnt_time;
        TextView txtVersion;
        RelativeLayout rl;
        ImageView logo,eventimg,orgimg,timeimg,venueimg,imgdesc;
        RelativeLayout cdv;
    }



    public CustomAdapterauto(ArrayList<DataModelauto> data, Context context) {
        super(context, R.layout.auto_list_adapter, data);
        this.dataSet = data;
        this.mContext=context;

    }


    @Override
    public void onClick(View v) {


        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModelauto dataModel=(DataModelauto)object;



//        switch (v.getId())
//        {
//
//            case R.id.imageViewshareauto:
//
//
//
//
//
//
////                Snackbar.make(v, "Release date "+ t +dataModel.getFeature(), Snackbar.LENGTH_LONG)
////                        .setAction("No action", null).show();
//
//
//                if (Build.VERSION.SDK_INT > 23) {
//
//
//                    Toast.makeText(mContext,"share the details", Toast.LENGTH_LONG).show();
////                Snackbar.make(v, "Release date "+ t +dataModel.getFeature(), Snackbar.LENGTH_LONG)
////                        .setAction("No action", null).show();
//
//
//
//
//
//                    Intent share = new Intent(Intent.ACTION_SEND);
//                    share.setType("text/plain");
//                    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//                    share. addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    // Add data to the intent, the receiving app will decide
//                    // what to do with it.
//                    String head="Auto rikhaw Details";
//                    share.putExtra(Intent.EXTRA_SUBJECT, head);
//                    share.putExtra(Intent.EXTRA_TEXT, "_*AUTO RIKSHAW DETAILS*_ \n"+"*"+dataModel.getName()+"*"+"\n"+"Rikshaw stand : _"+dataModel.getStand()+"_"+"\n "+"Contact number : "+dataModel.getPhone()+"\n \n  \n *Shared through : CONNECT MOODBIDRI APP*"+"\n \n  \n *Shared through : CONNECT MOODBIDRI APP*"+" \n \n Click the link below to Download *CONNECT MOODBIDRI* Android app from Google playstore \nhttps://play.google.com/store/apps/details?id=com.connect.moodbidripreethi.creativecodes");
//
//                    mContext.startActivity(Intent.createChooser(share, "Share details via!"));
//
//
//
//                    // Call some material design APIs here
//                } else {
//                    Toast.makeText(mContext, "This feature is'nt available,Required Nougat Android version and higher", Toast.LENGTH_SHORT).show();
//                    // Implement this feature without material design
//                }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////                String t=getCurrentTime();
//
////                Toast.makeText(mContext, t, Toast.LENGTH_LONG).show();
//
////                Snackbar.make(v, "Release date "+ t +dataModel.getFeature(), Snackbar.LENGTH_LONG)
////                        .setAction("No action", null).show();
//
//                break;
//
//
//        }


    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModelauto dataModel = getItem(position);
        if(position==posi) {
            // set some color

        }
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.auto_list_adapter, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.eventName);

            viewHolder.txtvenue = (TextView) convertView.findViewById(R.id.eventVenue);
            viewHolder.txtorg = (TextView) convertView.findViewById(R.id.eventorg);
            viewHolder.txttime=(TextView)convertView.findViewById(R.id.eventTime);
            viewHolder.timeimg = (ImageView) convertView.findViewById(R.id.imageviewtime);
            viewHolder.txtdesc=(TextView)convertView.findViewById(R.id.eventdesc);
            viewHolder.imgdesc = (ImageView) convertView.findViewById(R.id.imageviewdescription);

            viewHolder.venueimg = (ImageView) convertView.findViewById(R.id.imageviewvenue);
            viewHolder.eventimg = (ImageView) convertView.findViewById(R.id.imagevieweventname);
            viewHolder.orgimg = (ImageView) convertView.findViewById(R.id.imagevieworg);



//            viewHolder.phone_icon = (ImageView) convertView.findViewById(R.id.imageViewphoneauto);
           viewHolder.rl=(RelativeLayout)convertView.findViewById(R.id.eventnamerl);
         viewHolder.cdv=(RelativeLayout)convertView.findViewById(R.id.eventLayout);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.orgimg.setImageResource(R.drawable.ic_organiser);
        viewHolder.venueimg.setImageResource(R.drawable.ic_location);
        viewHolder.eventimg.setImageResource(R.drawable.ic_events);
        viewHolder.timeimg.setImageResource(R.drawable.ic_timeicon);
        viewHolder.imgdesc.setImageResource(R.drawable.ic_schedule_full);

        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtdesc.setText(dataModel.getDesc());
        viewHolder.txttime.setText(dataModel.getTime());
        viewHolder.txtvenue.setText(dataModel.getVenue());
        viewHolder.txtorg.setText(dataModel.getOrg());
        String time="";
        DateFormat inFormat = new SimpleDateFormat( "HH:mm" );
        Date date = null;
//        try
//        {
//            date = inFormat.parse(time);
//
//        }
//        catch ( ParseException e )
//        {
//            e.printStackTrace();
//        }
//
//        String a=dataModel.getInt_time().toString();
//        Toast.makeText(mContext, a, Toast.LENGTH_SHORT).show();

//        viewHolder.share.setOnClickListener(this);
//        viewHolder.share.setTag(position);
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
