package com.creativecodes.maheutsav2019;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Notifications extends AppCompatActivity implements AdapterView.OnItemClickListener {


    String one, two, three;
    ArrayList<HashMap<String, String>> detaillist;
    private static final String tag1 = "asd";
    private static final String tag2 = "erty";
    private static final String tag3 = "maha";
    Button getnews;
    ImageView sharenews;
    ListView listView;
    ImageView img;

    boolean connected = false;

    RequestQueue queue;

    StringRequest postRequest;
    String time;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtil.setTransparent(Notifications.this);
        setContentView(R.layout.activity_news_main);
        progressDialog = new ProgressDialog(Notifications.this);
        progressDialog.setMessage("Loading MAHE Utsav Notifications...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        listView = (ListView) findViewById(R.id.listnews);

        detaillist = new ArrayList<HashMap<String, String>>();
        listView.setOnItemClickListener(this);
        queue = Volley.newRequestQueue(this.getApplicationContext());
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            Runnable progressRunnable = new Runnable() {

                @Override
                public void run() {
                    if (progressDialog.isShowing()) {
                        progressDialog.cancel();
                        Toast.makeText(Notifications.this, "Oops,Server under maintainance..please try again later", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                }
            };

            Handler pdCanceller = new Handler();
            pdCanceller.postDelayed(progressRunnable, 5000);
            get_news();
            connected = true;


        } else {
            isNetworkConnectionAvailable();
            Toast.makeText(Notifications.this, "please check your internet connection", Toast.LENGTH_SHORT).show();
//            Intent intent=new Intent(Listmainsearch.this,Mainstoplist.class);
//            startActivity(intent);
        }


//
    }

    public void get_news() {


        postRequest = new StringRequest(Request.Method.POST, utility.ip + "get_notifications.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        if (response.equals("") || response == null || response.equals(" ")) {
                            Toast.makeText(Notifications.this, "no match", Toast.LENGTH_SHORT).show();
                        }

                        try {
                            JSONArray data = new JSONArray(response);
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject c = data.getJSONObject(i);
                                one = c.getString("heading");
                                two = c.getString("description");
                                three = c.getString("time");
//                                Glide.with(Notifications.this)
//                                        .load(utility.ip+"images/"+one)
//                                        .placeholder(R.drawable.news_logo)
//                                        .into(img);
                                HashMap<String, String> detaillist1 = new HashMap<String, String>();
                                detaillist1.put(tag1, one);
                                detaillist1.put(tag2, two);
                                detaillist1.put(tag3, three);
                                detaillist.add(detaillist1);


                            }
                            ListAdapter adapter1 = new SimpleAdapter(Notifications.this, detaillist, R.layout.news_card_custom_layout,
                                    new String[]{tag1, tag2, tag3},
                                    new int[]{R.id.newshead, R.id.textnewsdesc, R.id.textViewnewstime});
                            listView.setAdapter(adapter1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("src", src);
//                params.put("dest", dest);

                return params;
            }
        };
        queue.add(postRequest);


    }

    public void checkNetworkConnection() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to get updated notifications");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if (isConnected) {
            Log.d("Network", "Connected");
            return true;
        } else {
            checkNetworkConnection();
            Log.d("Network", "Not Connected");
            return false;
        }
    }


    public void startNewActivity(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null) {
            // We found the activity now start the activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            // Bring user to the market or let them choose an app?
            Toast.makeText(context, "KA19news app not installed", Toast.LENGTH_SHORT).show();
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + packageName));
            context.startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView te = (TextView) view.findViewById(R.id.newshead);
//        TextView busname = (TextView) view.findViewById(R.id.textnewsdesc);
//        TextView textViewnewstime = (TextView) view.findViewById(R.id.textViewnewstime);
////        Toast.makeText(Notifications.this, te.getText().toString(), Toast.LENGTH_LONG).show();
//        Intent share = new Intent(android.content.Intent.ACTION_SEND);
//        share.setType("text/plain");
//        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//
//        // Add data to the intent, the receiving app will decide
//        // what to do with it.
//        share.putExtra(Intent.EXTRA_SUBJECT, te.getText().toString());
//        share.putExtra(Intent.EXTRA_TEXT, "*" + te.getText().toString() + "*" + "\n \n" + "_" + busname.getText().toString() + "_" + "\n \n" + textViewnewstime.getText().toString() + "\n \n  \n *LATEST NEWS RELATED TO BUSES : PROVIDED BY CBUS*");
//
//        startActivity(Intent.createChooser(share, "Share CBUS news via!"));


    }

}