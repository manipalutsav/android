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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by anupamchugh on 09/02/16.
 */
public class CustomAdapter_bus extends ArrayAdapter<DataModel_bus> implements View.OnClickListener{

    private ArrayList<DataModel_bus> dataSet;
    Context mContext;
    int posi;
//    int hex = 0x45E213;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtclg;
        TextView des;
        int bustime,currnt_time;
        TextView txtVersion;
        RelativeLayout rl;
        ImageView clck,busimg;
        CardView cdv;
    }



    public CustomAdapter_bus(ArrayList<DataModel_bus> data, Context context) {
        super(context, R.layout.ccclist_adapter, data);
        this.dataSet = data;
        this.mContext=context;
    }


    @Override
    public void onClick(View v) {


        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModel_bus dataModel=(DataModel_bus)object;



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


        }


    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel_bus dataModel = getItem(position);
        if(position==posi) {
            // set some color

        }
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ccclist_adapter, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.ccc_name);
            viewHolder.txtclg = (TextView) convertView.findViewById(R.id.textccc_clg);

//            viewHolder.rl=(RelativeLayout)convertView.findViewById(R.id.rlbus);
            viewHolder.cdv=(CardView)convertView.findViewById(R.id.cardccc);
//            viewHolder.busimg=(ImageView)convertView.findViewById(R.id.imageViewbus);
//            viewHolder.clck=(ImageView)convertView.findViewById(R.id.imageViewclk);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

//        viewHolder.clck.setImageResource(R.drawable.time);
//        viewHolder.busimg.setImageResource(R.drawable.bus_time);
        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtclg.setText(dataModel.getCollege());
//        viewHolder.des.setText(dataModel.getDes());
//        String time=dataModel.getBtime();

//
//        String a=dataModel.getInt_time().toString();
//        Toast.makeText(mContext, a, Toast.LENGTH_SHORT).show();

//        viewHolder.clck.setOnClickListener(this);
//        viewHolder.clck.setTag(position);
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
