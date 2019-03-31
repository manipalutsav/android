package com.creativecodes.maheutsav2019;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.jaeger.library.StatusBarUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,NetworkStateReceiver.NetworkStateReceiverListener, ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener, AdapterView.OnItemClickListener {
    private SliderLayout mDemoSlider;
    utility utility,utility1,utility2,utility3,utility4;
    String ones,twos,threes;
    AutoCompleteTextView search;
    Button searchbtn, clear, btnjob;
    private NetworkStateReceiver networkStateReceiver;
    private GestureDetector mDetector;
    LinearLayout sliderrelative;
    GridView gridView;
    LinearLayout linearlayoutmain;

    ProgressDialog progressDialog,progressDialog1;
    StringRequest postRequest;
    WebView wb;
    RequestQueue queue,queue1,queue2,queue3,queue4;
    HashMap<String, String> url_maps_ads,url_maps_sponser;

    String letterList[] = {"Events", "Results", "Contact us", "Instagram", "Facebook", "About us"  ,"Facebook", "About us"};
    int lettersIcon[] = {R.drawable.ic_events, R.drawable.ic_results, R.drawable.ic_events, R.drawable.ic_about, R.drawable.ic_events, R.drawable.ic_events, R.drawable.ic_results, R.drawable.ic_contactus};

    @Override
    public void networkAvailable() {
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();

//        wb.loadUrl("https://www.facebook.com/mahe.utsav/");
        loadimages();

    }

    @Override
    public void networkUnavailable() {

        Toast.makeText(this, "Please check your internet connectivity", Toast.LENGTH_SHORT).show();

    }


    private class tourismHelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // For Orientation restricted to portrait
//        wb=(WebView)findViewById(R.id.webview);
//        wb.setWebContentsDebuggingEnabled(false);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider1);
        sliderrelative=(LinearLayout)findViewById(R.id.sliderlayout);
        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        queue = Volley.newRequestQueue(MainActivity.this.getApplicationContext());


        linearlayoutmain=(LinearLayout)findViewById(R.id.gridlayout);




        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

//        gridView = (GridView) findViewById(R.id.gridView);
//        GridAdapter gadapter = new GridAdapter(MainActivity.this, lettersIcon, letterList);
//        gridView.setAdapter(gadapter);
//        gridView.setOnItemClickListener(this);

//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
//                sliderrelative.getLayoutParams();
//        params.weight = 0;
//        sliderrelative.setLayoutParams(params);
//
//        LinearLayout.LayoutParams paramslay = (LinearLayout.LayoutParams)
//                sliderrelative.getLayoutParams();
//        paramslay.weight = 8;
//        sliderrelative.setLayoutParams(paramslay);



//        wb.getSettings().setUseWideViewPort(true);
//        wb.getSettings() .setLoadWithOverviewMode(true);
//        wb.getSettings().setJavaScriptEnabled(true);
//        wb.getSettings().setBuiltInZoomControls(true);
//        wb.getSettings().setPluginState(WebSettings.PluginState.ON);
////        wb.getSettings().setPluginsEnabled(true);
//        wb.setWebViewClient(new MainActivity.tourismHelloWebViewClient());
//        wb.loadUrl("https://www.facebook.com/mahe.utsav/");



        utility = new utility();
        progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("connecting to MAHE Utsav server");
        progressDialog.setTitle(" Retrieving server...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        mDetector = new GestureDetector(this, new MyGestureListener());


//        if (fragment == null) {
//            fragment = new HorizontalListViewFragment();
//            ;
//            fm.beginTransaction()
//                    .add(R.id.fragmentContainer, fragment)
//                    .commit();
//        }
        HashMap<String, Integer> url_maps = new HashMap<>();

        url_maps.put("MAHE Utsav 2018 ", R.drawable.slide7);

//
        url_maps_ads = new HashMap<>();
        url_maps_sponser = new HashMap<>();

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);

        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Left_Top);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(3000);
        mDemoSlider.addOnPageChangeListener(this);

//        search = (AutoCompleteTextView) findViewById(R.id.search);

//        search.setOnKeyListener(new View.OnKeyListener() {
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (event.getAction() == KeyEvent.ACTION_DOWN)
//                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) ||
//                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
//
//
////                        enteredtext=(String) search.getText().toString();
////                        Intent a=new Intent(MainActivity.this,result_list_main.class);
////                        a.putExtra("enteredtext",enteredtext);
////                        startActivity(a);
//
//
//
//                        //true because you handle the event
//                        return true;
//                    }
//                return false;
//            }
//        });




//        clear = (Button) findViewById(R.id.erase);

//        clear.setVisibility(View.INVISIBLE);

        //close button visibility for manual typing
//        search.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                //do nothing
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                //do nothing
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(s.length() != 0) {
//                    clear.setVisibility(View.VISIBLE);
//                } else {
//                    clear.setVisibility(View.GONE);
//                }
//            }
//        });
//
//





        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network







            Runnable progressRunnable3 = new Runnable() {

                @Override
                public void run() {
                    {

                       loadimages();
                        progressDialog.setMessage("Please wait...");
                        progressDialog.setTitle("Loading Images");

                    }

                }
            };
            Handler pdCanceller3 = new Handler();
            pdCanceller3.postDelayed(progressRunnable3, 2000);


            Runnable progressRunnable4 = new Runnable() {

                @Override
                public void run() {
                    {

                      //  loadimages();


                       progressDialog.dismiss();

                   //     Toast.makeText(MainActivity.this, "Failed to connect .. please try again after sometime", Toast.LENGTH_SHORT).show();





                    }

                }
            };
            Handler pdCanceller4 = new Handler();
            pdCanceller4.postDelayed(progressRunnable4, 5000);




//            get_news();

        } else {
            Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_LONG).show();
          progressDialog.dismiss();
//            progressDialog1.cancel();
//            progressDialog1.dismiss();
        }






    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }




    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        TextView textView=(TextView)view.findViewById(R.id.letters);

        if (textView.getText().toString().equals("Events")){
//            Toast.makeText(this,"Please enter source and Destination", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,Event_list.class);
            startActivity(intent);
        }


        if (textView.getText().toString().equals("KA Registration Zones")){
//            Toast.makeText(this,"Please enter source and Destination", Toast.LENGTH_SHORT).show();
//            Intent intent=new Intent(MainActivity.this,Mainreg.class);
//            startActivity(intent);
        }
        if (textView.getText().toString().equals("CBUS MAP")){
//            Toast.makeText(this,"Opening Map", Toast.LENGTH_SHORT).show();
////            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
//            get_cbus_map();


//            startActivity(intent);
        }
        if (textView.getText().toString().equals("NEWS")){
//            Toast.makeText(this,"Please enter source and Destination", Toast.LENGTH_SHORT).show();
//            Intent intent=new Intent(MainActivity.this,Notifications.class);
//            startActivity(intent);
        }

    }







    @Override
    public void onClick(View v) {

    }



    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }











    private void loadimages() {

        postRequest= new StringRequest(Request.Method.POST, utility.ip+"get_images.php",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {


                   //     Toast.makeText(MainActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
                        try {
                            JSONArray data = new JSONArray(response);
                            for (int i = 0; i < data.length(); i++) {
                                String dummy;
                                JSONObject c = data.getJSONObject(i);
                                ones = c.getString("image");
                                twos=c.getString("name");
                                threes=c.getString("name");
//                                Glide.with(MainActivity.this)
//                                        .load(utility.ip+"images/"+one)
//                                        .placeholder(R.drawable.bus_logo_app)
//                                        .into(image);

// url_maps.put("Vishal Motors", R.drawable.vishal_ad);
                                url_maps_ads.put(twos,utility3.ip+"images/"+ones);
//                                url_maps.put("Canara Pinto",R.drawable.canara_ad);
//                                url_maps.put("Sugama tourists",R.drawable.sugama_ad);


                            }

                            for (String name : url_maps_ads.keySet()) {
                                TextSliderView textSliderView = new TextSliderView(MainActivity.this);
                                // initialize a SliderLayout

                                textSliderView
                                        .description(name)
                                        .image(url_maps_ads.get(name))
                                        .setScaleType(BaseSliderView.ScaleType.Fit)
                                        .setOnSliderClickListener(MainActivity.this);

                                //add your extra information
                                textSliderView.bundle(new Bundle());
                                textSliderView.getBundle()
                                        .putString("extra", name);

                                mDemoSlider.addSlider(textSliderView);
//                                mDemoSlider.setVisibility(View.VISIBLE);

                                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Stack);
                                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Top);
                                mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                                mDemoSlider.setDuration(3500);
                                mDemoSlider.addOnPageChangeListener(MainActivity.this);




                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        // response
                        Log.d("Response", response);
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



                return params;
            }
        };
        queue.add(postRequest);

    }




    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown: " + event.toString());
    //        Toast.makeText(MainActivity.this, "ondown", Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
      //      Toast.makeText(MainActivity.this,"X:"+velocityX+" y:"+velocityY, Toast.LENGTH_SHORT).show();

            if(velocityY>0 && velocityX>0)
            {
                sliderrelative.setVisibility(View.VISIBLE);

            }
            else
            {
                sliderrelative.setVisibility(View.GONE);


            }

            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
          //  Toast.makeText(MainActivity.this, "fling", Toast.LENGTH_SHORT).show();
            return true;
        }
    }





}

