package com.creativecodes.maheutsav2019;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class CCClist extends AppCompatActivity implements AdapterView.OnItemClickListener {


    String one, two, three;
    ArrayList<HashMap<String, String>> detaillist;
    private static final String tag1 = "asd";
    private static final String tag2 = "erty";
    private static final String tag3 = "maha";
    Button getnews;
    ImageView sharenews;
    ListView listView1;
    ImageView img;
    ArrayList<DataModel_bus> dataModelBuses;
    private  CustomAdapter_bus adapter1;
    ArrayList<Integer> list;
    boolean connected = false;
    private String TAG = MainActivity.class.getSimpleName();


    RequestQueue queue;
    String name,collagename;

    StringRequest postRequest;
    String time;
    ProgressDialog progressDialog;
    private static String url = "https://api.manipalutsav.com/intruder/users";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ccclist);


        listView1 = (ListView) findViewById(R.id.listccc);



        detaillist = new ArrayList<HashMap<String, String>>();
        dataModelBuses = new ArrayList<>();

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            Runnable progressRunnable = new Runnable() {

                @Override
                public void run() {
                    if (progressDialog.isShowing()) {
                        progressDialog.cancel();
                        Toast.makeText(CCClist.this, "Oops,Server under maintainance..please try again later", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                }
            };

            Handler pdCanceller = new Handler();
            pdCanceller.postDelayed(progressRunnable, 5000);
            new CCClist.GetCCC().execute();

//            get_news();
            connected = true;


        } else {
            isNetworkConnectionAvailable();
            Toast.makeText(CCClist.this, "please check your internet connection", Toast.LENGTH_SHORT).show();
//
        }

//
    }

//
    public void checkNetworkConnection() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection");
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




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        TextView te = (TextView) view.findViewById(R.id.newshead);
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




    private class GetCCC extends AsyncTask<Void, Void, Void> {
//
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog

            progressDialog = new ProgressDialog(CCClist.this);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("Please wait...");
            progressDialog.setTitle("Loading CCC list ");
            progressDialog.setCancelable(true);
            progressDialog.show();


        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            final String jsonStr = sh.makeServiceCall(url);


//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(getApplicationContext(),
//                          jsonStr,
//                            Toast.LENGTH_LONG)
//                            .show();
//
//
//
////
//                }
//            });

//            Log.e(TAG, "Response from url: " + jsonStr);
//            Toast.makeText(jsoncall.this, jsonStr, Toast.LENGTH_SHORT).show();

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray events = jsonObj.getJSONArray("data");
//                    JSONArray col = jsonObj.getJSONObject("data").getJSONArray("college");
//
                    for (int i = 0; i < events.length(); i++) {

//
                        JSONObject c = events.getJSONObject(i);

//                        id = c.getString("round");
//                        name = c.getString("name");
//                        collagename = c.getString("college");
//                        venue = c.getString("venue");
//                        description = c.getString("description");

//
                        name = c.getString("name");

                        collagename = c.getString("college");






                            dataModelBuses.add(new DataModel_bus(name,collagename,name));








                    }


                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

//            ListAdapter adapter1 = new SimpleAdapter(CCClist.this, detaillist, R.layout.ccclist_adapter,
//                    new String[]{name, collagename},
//                    new int[]{R.id.ccc_name, R.id.textccc_clg});
//            listView.setAdapter(adapter1);

            progressDialog.dismiss();


            adapter1= new CustomAdapter_bus(dataModelBuses,getApplicationContext());

            listView1.setAdapter(adapter1);
//                               String n=getCategoryPos(String.valueOf(list.get(gv)));
//                                Toast.makeText(Listmainsearch.this,position, Toast.LENGTH_LONG).show();
//                                listView.setSelectionFromTop(n,0);


            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    DataModel_bus dataModel= dataModelBuses.get(position);

//                                    Snackbar.make(view, dataModel.getName()+"\t Stand name"+dataModel.getStand()+" Phone Number : "+dataModel.getPhone(), Snackbar.LENGTH_LONG)
//                                            .setAction("No action", null).show();
//
//                                    TextView nametext=(TextView)view.findViewById(R.id.textViewautocontact);
//                                    TextView name_auto=(TextView)view.findViewById(R.id.textViewautoname);
//                                    String n=(String) name_auto.getText().toString();
//                                    Toast.makeText(Event_list.this, "Call to : "+n, Toast.LENGTH_LONG).show();
//                                    con=(String) nametext.getText().toString();
//        Toast.makeText(this,con , Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(Intent.ACTION_DIAL);
//                    intent.setData(Uri.parse("tel:"+con));
//                    startActivity(intent);
//


//                                    TextView te=(TextView)view.findViewById(R.id.busidtext);
//                                    TextView busname=(TextView)view.findViewById(R.id.textViewbusname);
////        Toast.makeText(this, te.getText().toString()+"is id of selected bus", Toast.LENGTH_SHORT).show();
//                                    Intent intent=new Intent(Listmainsearch.this,Mainstoplist.class);
//                                    intent.putExtra("bus_id", te.getText().toString());
//                                    intent.putExtra("bus_name", busname.getText().toString());
//                                    startActivity(intent);
                }
            });



        }

    }




}