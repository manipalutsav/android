package com.creativecodes.maheutsav2019;

import android.app.AlertDialog;
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
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jaeger.library.StatusBarUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

public class Event_list extends AppCompatActivity {

    private TextView mTextMessage;

    String enteredtext,enteredtex;
    ArrayList<DataModelauto> dataModelautos;
    private static CustomAdapterauto adapter;
    ArrayList<Integer> list;
    int position;
    String con;

    String one,two,three,four,five,six,seven,eight;
    ArrayList<HashMap<String, String>> detaillist;
    private static final String tag1 = "asd";
    private static final String tag2 = "erty";
    private static final String tag3 = "maha";
    private static final String tag4 = "mxzbzaha";
    private static final String tag5 = "madsgvha";
    private static final String tag6 = "mxzgdbbzaha";
    private static final String tag7 = "mdvgbxzgdbbzaha";
    private static final String tag8= "mdvgbxbjlzgdbbzaha";
    ListView listView;
    String name;
    utility utility;
    boolean connected = false;
    String stand;
    RequestQueue queue ;

    StringRequest postRequest;
    String id;
    String[] searchresult;
    String latestVersion;
    String type;
    ProgressDialog progressDialog;
    String bus_name,route,checkdatestr;

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    String eventid,eventname,venue,collagename,description,eventdate,enddate;
    // URL to get contacts JSON
 //   private static String url = "http://192.168.0.11:3003/intruder/events";
    private static String url = "https://api.manipalutsav.com/intruder/events";
//    private static String url = "https://connectmoodbidri.com/mu/events.json";


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            dataModelautos.clear();

            switch (item.getItemId()) {
                case R.id.navigation_staff_utsav:
                    mTextMessage.setText(R.string.title_staff_utsav);
//                    searchautos();
//                    list.clear();
//                    searchautos();
                    new GetContacts("2019-04-01").execute();

                    return true;

                case R.id.navigation_day1:
                    mTextMessage.setText(R.string.title_day1);

                    new GetContacts("2019-04-02").execute();
                    return true;
                case R.id.navigation_day2:
                    mTextMessage.setText(R.string.title_day2);
                    new GetContacts("2019-04-03").execute();
                    return true;
                case R.id.navigation_day3:
                    new GetContacts("2019-04-04").execute();
                    mTextMessage.setText(R.string.title_day3);
                    return true;
                case R.id.navigation_day4:
                    new GetContacts("2019-04-05").execute();
                    mTextMessage.setText(R.string.title_day4);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(0);

        type= getIntent().getStringExtra("type");
        VersionChecker versionChecker = new VersionChecker();
        try {
             latestVersion = versionChecker.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        Toast.makeText(this,latestVersion , Toast.LENGTH_SHORT).show();

//        StatusBarUtil.setTransparent(Event_list.this);

//        stand=getIntent().getStringExtra("stand_name");
        detaillist = new ArrayList<HashMap<String, String>>();
        utility = new utility();
        queue = Volley.newRequestQueue(this);


//
//        progressDialog = new ProgressDialog(Event_list.this);
////        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progressDialog.setMessage("Please wait...");
//        progressDialog.setTitle(" Fetching data from server");
//        progressDialog.setCancelable(false);
//        progressDialog.show();


        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                if(progressDialog.isShowing())
                {
                    progressDialog.cancel();
                    Toast.makeText(Event_list.this, "cannot complete your request,try again after sometime", Toast.LENGTH_SHORT).show();
                    finish();

                }

            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 10000);






        listView = (ListView) findViewById(R.id.listautos);


        dataModelautos = new ArrayList<>();



        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
            new GetContacts("2019-04-01").execute();

//            searchautos();
            //Toast.makeText(result_list_main.this,"Results Found", Toast.LENGTH_SHORT).show();
        } else {
//            progressDialog.dismiss();
            Toast.makeText(Event_list.this, "please check your internet connection", Toast.LENGTH_SHORT).show();
           // Intent intent = new Intent(Event_list.this, No_connection.class);
          //  startActivity(intent);
        }

    }



    public   void searchautos()
    {


        postRequest = new StringRequest(Request.Method.POST, utility.ip +"events.json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Event_list.this, response.toString(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        if (response.equals("null") || response == null || response.equals("[]") || response.equals(" ")) {
                            Toast.makeText(Event_list.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
//                         Toast.makeText(result_list_main.this, response, Toast.LENGTH_SHORT).show();

                        if (response != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                Toast.makeText(Event_list.this, "generated", Toast.LENGTH_SHORT).show();
                                JSONArray events = jsonObj.getJSONArray("data");
//                    JSONArray col = jsonObj.getJSONObject("data").getJSONArray("college");
//
                                for (int i = 0; i < events.length(); i++) {

//
                                    JSONObject c = events.getJSONObject(i);
//

                                    id = c.getString("round");
                                    name = c.getString("name");
                                    collagename = c.getString("college");
                                    venue = c.getString("venue");
                                    description = c.getString("description");

                                    dataModelautos.add(new DataModelauto(id,name,collagename,venue,description,id));





                                }

                                adapter= new CustomAdapterauto(dataModelautos,getApplicationContext());

                                listView.setAdapter(adapter);
//                               String n=getCategoryPos(String.valueOf(list.get(gv)));
//                                Toast.makeText(Listmainsearch.this,position, Toast.LENGTH_LONG).show();
//                                listView.setSelectionFromTop(n,0);

                                progressDialog.dismiss();


                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        DataModelauto dataModel= dataModelautos.get(position);

//                                    Snackbar.make(view, dataModel.getName()+"\t Stand name"+dataModel.getStand()+" Phone Number : "+dataModel.getPhone(), Snackbar.LENGTH_LONG)
//                                            .setAction("No action", null).show();
//
//                                    TextView nametext=(TextView)view.findViewById(R.id.textViewautocontact);
//                                    TextView name_auto=(TextView)view.findViewById(R.id.textViewautoname);
//                                    String n=(String) name_auto.getText().toString();
//                                    Toast.makeText(Event_list.this, "Call to : "+n, Toast.LENGTH_LONG).show();
//                                    con=(String) nametext.getText().toString();
//        Toast.makeText(this,con , Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(Intent.ACTION_DIAL);
//                                        intent.setData(Uri.parse("tel:"+con));
//                                        startActivity(intent);



//                                    TextView te=(TextView)view.findViewById(R.id.busidtext);
//                                    TextView busname=(TextView)view.findViewById(R.id.textViewbusname);
////        Toast.makeText(this, te.getText().toString()+"is id of selected bus", Toast.LENGTH_SHORT).show();
//                                    Intent intent=new Intent(Listmainsearch.this,Mainstoplist.class);
//                                    intent.putExtra("bus_id", te.getText().toString());
//                                    intent.putExtra("bus_name", busname.getText().toString());
//                                    startActivity(intent);
                                    }
                                });


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
//                params.put("day","staff_utsav");
////                params.put("dest", dest);

                return params;
            }
        };
        queue.add(postRequest);



    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        String day,tempdate;

        GetContacts(String eventdate){

            day=eventdate;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog

            progressDialog = new ProgressDialog(Event_list.this);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("Please wait...");

            progressDialog.setTitle("Loading events ");
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

            Log.e(TAG, "Response from url: " + jsonStr);
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
                        id = c.getString("round");
                        eventdate = c.getString("startDate");
                        collagename = c.getString("college");
                        venue = c.getString("venue");
                        enddate= c.getString("endDate");
                        description = c.getString("description");
                        DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                        utcFormat.setTimeZone(TimeZone.getTimeZone("GMT"));


                        //TO ADD MCOM COLLEGE
                        if(id.equals("5c89ef416a14fe5068eae00d"))
                        {

                            collagename=collagename+" , MCON";

                        }

                        Date date = null;
                        Date edate=null;
                        try {
                            date = utcFormat.parse(eventdate);

                            edate = utcFormat.parse(enddate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        DateFormat pstFormat = new SimpleDateFormat("yyyy-MM-dd");
                        pstFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));

                        DateFormat pstFormat1 = new SimpleDateFormat("dd.MMM.yyyy 'at' hh:mm aa");
                        pstFormat1.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
                        eventdate=pstFormat1.format(date);
                        enddate=pstFormat1.format(edate);

                        tempdate= pstFormat.format(date);
                        String tempedate= pstFormat.format(edate);

//                        String sourceDateTime           = "2018-05-23T23:18:31.000Z";
//                        DateTimeFormatter sourceFormat  = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//                        DateTimeFormatter targetFormat  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//                        LocalDateTime dateTime          = LocalDateTime.parse(sourceDateTime, sourceFormat);
//                        String formatedDateTime         = dateTime.atZone(ZoneId.of("UTC")).format(targetFormat);



                        System.out.println(tempdate);


                        if(tempdate.equals(day) || tempedate.equals(day))
                        {
                            dataModelautos.add(new DataModelauto(name,eventdate,venue,collagename,description,id));
                        }







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
            // Dismiss the progress dialog


            adapter= new CustomAdapterauto(dataModelautos,getApplicationContext());

            listView.setAdapter(adapter);
//                               String n=getCategoryPos(String.valueOf(list.get(gv)));
//                                Toast.makeText(Listmainsearch.this,position, Toast.LENGTH_LONG).show();
//                                listView.setSelectionFromTop(n,0);

            progressDialog.dismiss();


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    DataModelauto dataModel= dataModelautos.get(position);

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
String des=dataModel.getDesc();
String eventname=dataModel.getName();
//String eventname=dataModel.getName();
//                                    TextView te=(TextView)view.findViewById(R.id.busidtext);
//                                    TextView busname=(TextView)view.findViewById(R.id.textViewbusname);
////        Toast.makeText(this, te.getText().toString()+"is id of selected bus", Toast.LENGTH_SHORT).show();
//                                    Intent intent=new Intent(Event_list.this,Results.class);
//                                    intent.putExtra("description", des);
//                                    startActivity(intent);

                    AlertDialog alertDialog = new AlertDialog.Builder(Event_list.this).create();
                    alertDialog.setTitle("Description of "+eventname+" event");
                    alertDialog.setMessage(des);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "CLOSE",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }
            });
            /**
             * Updating parsed JSON data into ListView
             * */
//            ListAdapter adapter = new SimpleAdapter(
//                    jsoncall.this, contactList,
//                    R.layout.list_item, new String[]{"name", "email",
//                    "mobile"}, new int[]{R.id.name,
//                    R.id.email, R.id.mobile});





//            lv.setAdapter(adapter);
        }

    }
    public class VersionChecker extends AsyncTask<String, String, String> {

        String newVersion;

        @Override
        protected String doInBackground(String... params) {

            try {
                newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=com.connect.moodbidripreethi.creativecodes&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                        .first()
                        .ownText();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return newVersion;
        }

    }
}
