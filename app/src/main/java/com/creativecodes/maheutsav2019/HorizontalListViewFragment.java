package com.creativecodes.maheutsav2019;

/**
 * Created by anonymous on 11/4/16.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class HorizontalListViewFragment extends Fragment {

    ArrayList<Fruit> listitems = new ArrayList<>();
    RecyclerView MyRecyclerView;
    String Fruits[] = {"Events","Results","Contact Us","Feedback","Instagram","About Us"};
    int  Images[] = {R.drawable.ic_events,R.drawable.ic_results,R.drawable.ic_contactus,R.drawable.ic_feedback_icon,R.drawable.ic_instagram,R.drawable.ic_about};
ImageView arowimg,arowimgleft;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listitems.clear();
        for(int i =0;i<Fruits.length;i++){
            Fruit item = new Fruit();
            item.setCardName(Fruits[i]);
            item.setImageResourceId(Images[i]);
            item.setIsfav(0);
            item.setIsturned(0);
            listitems.add(item);
        }

        getActivity().setTitle("MAHE Utsav 2019");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_horizontal_list_view, container, false);
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if (listitems.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(listitems));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);




        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //arowimg=(ImageView) getActivity().findViewById(R.id.imageViewarrow);
      //  arowimgleft=(ImageView) getActivity().findViewById(R.id.imageViewarrowstart);


    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<Fruit> list;

        public MyAdapter(ArrayList<Fruit> Data) {
            list = Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_items, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.titleTextView.setText(list.get(position).getCardName());
            holder.coverImageView.setImageResource(list.get(position).getImageResourceId());
            holder.coverImageView.setTag(list.get(position).getImageResourceId());
//            holder.likeImageView.setTag(R.drawable.ic_like);
//            if(position==0)
//            {
//              holder.  titleTextView.setTextColor(Color.parseColor("#D7EA3838"));
//
//            }





            if(position>3)
            {
                arowimg.setVisibility(View.INVISIBLE);
            }
            else
            {
                arowimg.setVisibility(View.VISIBLE);
            }


            if( position>5)
            {
                arowimgleft.setVisibility(View.VISIBLE);

            }
            else
            {
                arowimgleft.setVisibility(View.INVISIBLE);
            }




        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView titleTextView;
        public ImageView coverImageView;
        public RelativeLayout relativeLayout;
        public ImageView likeImageView;
        public ImageView shareImageView;

        public MyViewHolder(View v) {
            super(v);
            titleTextView = (TextView) v.findViewById(R.id.titleTextView);
            coverImageView = (ImageView) v.findViewById(R.id.coverImageView);
//            likeImageView = (ImageView) v.findViewById(R.id.likeImageView);
//            shareImageView = (ImageView) v.findViewById(R.id.shareImageView);
            relativeLayout=(RelativeLayout) v.findViewById(R.id.rllist);
//            cardViewstop.setOnClickListener(this);




















            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TextView textView = (TextView)v.findViewById(R.id.titleTextView);
//                     Toast.makeText(getContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
                    if (textView.getText().toString().equals("Events")) {


                        ConnectivityManager connectivityManager = (ConnectivityManager) getContext() .getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //we are connected to a network


                            Intent intent = new Intent(getContext(), Event_list.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
                            checkNetworkConnection();
                        }






//            Toast.makeText(this,"Please enter source and Destination", Toast.LENGTH_SHORT).show();

                    }
                    if (textView.getText().toString().equals("Instagram")) {
//https://www.instagram.com/mahe.utsav/

                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://www.instagram.com/mahe.utsav/"));
                            intent.setPackage("com.instagram.android");
                            startActivity(intent);
                        }
                        catch (android.content.ActivityNotFoundException anfe)
                        {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.instagram.com/" + "mahe.utsav")));
                        }




                    }
                    if (textView.getText().toString().equals("Feedback")) {
                        Toast.makeText(getContext(), "Provide your valuable feedback", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getContext(),Feedback.class);
            startActivity(intent);
                    }
                    if (textView.getText().toString().equals("Connect Bus")) {
//            Toast.makeText(this,"C BUS news", Toast.LENGTH_SHORT).show();

                        ConnectivityManager connectivityManager = (ConnectivityManager) getContext() .getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //we are connected to a network


//                            Intent intent = new Intent(getContext(), Bus_search.class);
//                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
                            checkNetworkConnection();
                        }




                    }
                    if (textView.getText().toString().equals("Connect Rikshaw")) {
//            Toast.makeText(this,"C BUS news", Toast.LENGTH_SHORT).show();


                        ConnectivityManager connectivityManager = (ConnectivityManager) getContext() .getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //we are connected to a network


//                            Intent intent = new Intent(getContext(), auto_rikshaw.class);
//                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
                            checkNetworkConnection();
                        }



                    }



                    if (textView.getText().toString().equals("Connect Events")) {
//            Toast.makeText(this,"C BUS news", Toast.LENGTH_SHORT).show();


                        ConnectivityManager connectivityManager = (ConnectivityManager) getContext() .getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //we are connected to a network


//                            Intent intent = new Intent(getContext(), events_activity.class);
//                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
                            checkNetworkConnection();
                        }



                    }




















                    if (textView.getText().toString().equals("Panchayat Connect")) {
//            Toast.makeText(this,"C BUS news", Toast.LENGTH_SHORT).show();

                        ConnectivityManager connectivityManager = (ConnectivityManager) getContext() .getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //we are connected to a network


//                            Intent intent=new Intent(getContext(),Panchayat_activity.class);
//                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
                            checkNetworkConnection();
                        }




//                        Toast.makeText(getContext(), "This feature isn't avalaible, Wait for updates", Toast.LENGTH_SHORT).show();

                    }
                    if (textView.getText().toString().equals("Job Connect")) {


                        ConnectivityManager connectivityManager = (ConnectivityManager) getContext() .getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //we are connected to a network


//                            Intent intent = new Intent(getContext(), job_option_activity.class);
//                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
                            checkNetworkConnection();
                        }



//            Toast.makeText(this,"C BUS news", Toast.LENGTH_SHORT).show();

                    }
                    if (textView.getText().toString().equals("Connect Map")) {
//            Toast.makeText(this,"C BUS news", Toast.LENGTH_SHORT).show();



                        ConnectivityManager connectivityManager = (ConnectivityManager) getContext() .getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //we are connected to a network


//                            Intent intent = new Intent(getContext(), map_webview.class);
//                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
                            checkNetworkConnection();
                        }









                    }
                    if (textView.getText().toString().equals("More options")) {
//            Toast.makeText(this,"C BUS news", Toast.LENGTH_SHORT).show();

                        final CharSequence colors[] = new CharSequence[]{"Advertise with us", "Invite friend", "Call us", "Suggestions", "About us"};
//
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setItems(colors, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (colors[which].toString().equals("Advertise with us")) {
//                                    Intent intent = new Intent(getContext(), addcontactActivity.class);
//                                    startActivity(intent);
//



                                }
                                if (colors[which].toString().equals("Invite friend")) {


                                    Intent share = new Intent(Intent.ACTION_SEND);
                                    share.setType("text/plain");
                                    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                                    // Add data to the intent, the receiving app will decide
                                    // what to do with it.
                                    String head="";
                                    share.putExtra(Intent.EXTRA_SUBJECT, head);
                                    share.putExtra(Intent.EXTRA_TEXT, "Click the link below to Download *CONNECT MOODBIDRI* Android app from Google playstore \nhttps://play.google.com/store/apps/details?id=com.connect.moodbidripreethi.creativecodes");
                                    startActivity(Intent.createChooser(share, "Share details via!"));


















//                                    shareApplication();
                                }
                                if (colors[which].toString().equals("Call us")) {
                                    String  num="9844498098";
                                    Toast.makeText(getContext(),"Calling 9844498098 - Naveen kumar" , Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:"+num));
                                    startActivity(intent);
                                }
                                if (colors[which].toString().equals("Modify Contact")) {
//
//                            Intent intent = new Intent(getContext(),Update_bus_stop.class);
//                            intent.putExtra("bus_id",busidtxt);
//                            intent.putExtra("route_name",routetxt);
//                            intent.putExtra("bustype",typetxt);
//                            startActivity(intent);
//                            finish();
//
                                }
                                if (colors[which].toString().equals("Suggestions")) {
                                    Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                                    intent.setType("text/plain");
                                    intent.putExtra(Intent.EXTRA_SUBJECT, "Review/Suggestion");
                                    intent.putExtra(Intent.EXTRA_TEXT, "(From connect moodbidri App): ");
                                    intent.setData(Uri.parse("mailto:connectmoodbidri@gmail.com")); // or just "mailto:" for blank
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                                    startActivity(intent);


                                }
                                if (colors[which].toString().equals("About us")) {
//
                                  //  Intent intent = new Intent(getContext(), About_us.class);
                                  //  startActivity(intent);
                                }
                            }

                            private void update(final String s) {

                            }

                            //
                            private void delete(final String busid) {
//
                            }


                        });
                        AlertDialog dialog = builder.create();
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

                        wmlp.gravity = Gravity.TOP;

                        builder.show();


                    }



                }
            });



//            shareImageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                            "://" + getResources().getResourcePackageName(coverImageView.getId())
//                            + '/' + "drawable" + '/' + getResources().getResourceEntryName((int)coverImageView.getTag()));
//
//
//                    Intent shareIntent = new Intent();
//                    shareIntent.setAction(Intent.ACTION_SEND);
//                    shareIntent.putExtra(Intent.EXTRA_STREAM,imageUri);
//                    shareIntent.setType("image/jpeg");
//                    startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
//
//
//
//                }
//            });



        }

        @Override
        public void onClick(View view) {

        }



        void checkNetworkConnection(){
            android.support.v7.app.AlertDialog.Builder builder =new android.support.v7.app.AlertDialog.Builder(getContext());
            builder.setTitle("No internet Connection");
            builder.setMessage("Please turn on internet connection to continue");
            builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            android.support.v7.app.AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }



    }



    private void shareApplication() {
        ApplicationInfo app = getContext().getApplicationContext().getApplicationInfo();
        String filePath = app.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);

        // MIME of .apk is "application/vnd.android.package-archive".
        // but Bluetooth does not accept this. Let's use "*/*" instead.
        intent.setType("*/*");

        // Append file and send Intent
        File originalApk = new File(filePath);

        try {
            //Make new directory in new location
            File tempFile = new File(getContext().getExternalCacheDir() + "/ExtractedApk");
            //If directory doesn't exists create new
            if (!tempFile.isDirectory())
                if (!tempFile.mkdirs())
                    return;
            //Get application's name and convert to lowercase
            tempFile = new File(tempFile.getPath() + "/" + getString(app.labelRes).replace(" ","").toLowerCase() + ".apk");
            //If file doesn't exists create new
            if (!tempFile.exists()) {
                if (!tempFile.createNewFile()) {
                    return;
                }
            }
            //Copy file to new location
            InputStream in = new FileInputStream(originalApk);
            OutputStream out = new FileOutputStream(tempFile);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
            //Open share dialog
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
            startActivity(Intent.createChooser(intent, "Share app via"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}