package com.creativecodes.maheutsav2019;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Results extends AppCompatActivity {




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

    String round,event;
    String eventid,eventname,venue,collagename,description,eventdate,enddate;
    // URL to get contacts JSON
    //   private static String url = "http://192.168.0.11:3003/intruder/events";
    private  String url = "https://staging.manipalutsav.com/intruder/events/5c8b79b4c08d4f611524e193/rounds/5c9203e7b3a4a3131dcd0c17/leaderboard";

//    private static String url = "https://connectmoodbidri.com/mu/events.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_list);
//        https://staging.manipalutsav.com/intruder/slots/5c89ef416a14fe5068eadff9
        detaillist = new ArrayList<HashMap<String, String>>();

        listView = (ListView) findViewById(R.id.listslots);
        round = getIntent().getStringExtra("round");
        event = getIntent().getStringExtra("event");
//        url=url+round;
//        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();

        dataModelautos = new ArrayList<>();



        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
            new GetResult().execute();

//            searchautos();
            //Toast.makeText(result_list_main.this,"Results Found", Toast.LENGTH_SHORT).show();
        } else {
//            progressDialog.dismiss();
            Toast.makeText(Results.this, "please check your internet connection", Toast.LENGTH_SHORT).show();
            // Intent intent = new Intent(Event_list.this, No_connection.class);
            //  startActivity(intent);
        }

    }



    private class GetResult extends AsyncTask<Void, Void, Void> {
        String day,tempdate;

//        GetResult(String slot){
//
//            day=eventdate;
//
//        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog

            progressDialog = new ProgressDialog(Results.this);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("Please wait...");

            progressDialog.setTitle("Loading Slots");
            progressDialog.setCancelable(true);
            progressDialog.show();


        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            final String jsonStr = sh.makeServiceCall(url);


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                          jsonStr,
                            Toast.LENGTH_LONG)
                            .show();



//
                }
            });

            Log.e(TAG, "Response from url: " + jsonStr);
//            Toast.makeText(jsoncall.this, jsonStr, Toast.LENGTH_SHORT).show();

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

//                    JSONArray events = jsonObj.getJSONArray("data.team");
                    JSONArray col = jsonObj.getJSONArray("data");
                    for (int i = 0; i < col.length(); i++) {
                        JSONObject n = col.getJSONObject(i);



//                        id = c.getString("round");
//                        name = c.getString("name");
//                        collagename = c.getString("college");
//                        venue = c.getString("venue");
//                        description = c.getString("description");

//

                        two = n.getString("points");



                        HashMap<String, String> detaillist1 = new HashMap<String, String>();
                        detaillist1.put(tag1, one);
                        detaillist1.put(tag2, "Points : "+two);
                        detaillist.add(detaillist1);


                    }
//
//                    for (int i = 0; i < events.length(); i++) {
//
////
//                        JSONObject c = events.getJSONObject(i);
//                        JSONObject n = col.getJSONObject(i);
//
//
//
////                        id = c.getString("round");
////                        name = c.getString("name");
////                        collagename = c.getString("college");
////                        venue = c.getString("venue");
////                        description = c.getString("description");
//
////
//                        one = n.getString("name");
//                        two = c.getString("points");
//                        three = c.getString("disqualified");
//
//
//
//                        HashMap<String, String> detaillist1 = new HashMap<String, String>();
//                        detaillist1.put(tag1, one);
//                        detaillist1.put(tag2, "Points : "+two);
//                        detaillist.add(detaillist1);
//
//
//                    }
//

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



            ListAdapter adapter1 = new SimpleAdapter(Results.this, detaillist, R.layout.slot_adapter,
                    new String[]{tag1, tag2},
                    new int[]{R.id.slotteamname, R.id.textslotnumber});
            listView.setAdapter(adapter1);


//            adapter= new CustomAdapterauto(dataModelautos,getApplicationContext());
//
//            listView.setAdapter(adapter);
////                               String n=getCategoryPos(String.valueOf(list.get(gv)));
////                                Toast.makeText(Listmainsearch.this,position, Toast.LENGTH_LONG).show();
////                                listView.setSelectionFromTop(n,0);

            progressDialog.dismiss();


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                    DataModelauto dataModel= dataModelautos.get(position);

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


//                    String roundid=dataModel.getId();
//                    String eventname=dataModel.getName();
//                                    TextView te=(TextView)view.findViewById(R.id.busidtext);
//                                    TextView busname=(TextView)view.findViewById(R.id.textViewbusname);
////        Toast.makeText(this, te.getText().toString()+"is id of selected bus", Toast.LENGTH_SHORT).show();
//                                    Intent intent=new Intent(Event_list.this,Mainstoplist.class);
//                                    intent.putExtra("bus_id", te.getText().toString());
//                                    startActivity(intent);
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
}
