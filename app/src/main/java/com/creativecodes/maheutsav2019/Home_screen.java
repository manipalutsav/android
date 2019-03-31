package com.creativecodes.maheutsav2019;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Home_screen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,View.OnClickListener,NetworkStateReceiver.NetworkStateReceiverListener, ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener, AdapterView.OnItemClickListener {

    private static int SPLASH_TIME_OUT = 3000;

    private SliderLayout mDemoSlider;
    utility utility,utility1,utility2,utility3,utility4;
    String ones,twos,threes;
    AutoCompleteTextView search;
    Button searchbtn, clear, btnjob;
    private NetworkStateReceiver networkStateReceiver;
    private GestureDetector mDetector;
    LinearLayout sliderrelative;
    String latestVersion;
    GridView gridView;

    LinearLayout linearlayoutmain;
    TextView scroll_text_main;
    String version_name=BuildConfig.VERSION_NAME;
    ImageView imageView;
    ProgressDialog progressDialog,progressDialog1;
    StringRequest postRequest;
    WebView wb;
    RequestQueue queue,queue1,queue2,queue3,queue4;
    HashMap<String, String> url_maps_ads,url_maps_sponser;

    String letterList[] = {"General Rules","Events", "Utsav Schedule","Results", "Instagram", "Facebook", "Venue Map"  ,"Notifications"};
    int lettersIcon[] = {R.drawable.ic_rules, R.drawable.ic_events, R.drawable.ic_schedule_full, R.drawable.ic_results, R.drawable.ic_instagram, R.drawable.ic_facebook, R.drawable.ic_location, R.drawable.ic_notifications};





    @Override
    public void networkAvailable() {
//        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();

//        wb.loadUrl("https://www.facebook.com/mahe.utsav/");
//        loadimages();


        Runnable progressRunnable3 = new Runnable() {

            @Override
            public void run() {
                {

                        loadimages();


                }

            }
        };
        Handler pdCanceller3 = new Handler();
        pdCanceller3.postDelayed(progressRunnable3, 3000);


    }

    @Override
    public void networkUnavailable() {

        Toast.makeText(this, "Please check your internet connectivity", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        checkNetworkConnection();

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
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

VersionCheckerapp versionChecker = new VersionCheckerapp();
        try {
            latestVersion = versionChecker.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        Toast.makeText(this,latestVersion , Toast.LENGTH_SHORT).show();




        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
//     navigationView.setSelected(true);
        scroll_text_main=(TextView) findViewById(R.id.scroll_text);
scroll_text_main.setSelected(true);
setMarqueeSpeed(scroll_text_main,50);
  //     setTickerAnimation(scroll_text_main);


        imageView=(ImageView)findViewById(R.id.imgeutsav);
//        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
//        imageView.setAnimation(animation);

//        new Handler().postDelayed(new Runnable() {
//
//
//            @Override
//            public void run() {
//                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
//                imageView.setAnimation(animation);
//
//
//            }
//        }, SPLASH_TIME_OUT);
//
//        new Handler().postDelayed(new Runnable() {
//
//
//            @Override
//            public void run() {
//                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
//                imageView.setAnimation(animation);
//
//
//            }
//        }, 6000);
//
//        new Handler().postDelayed(new Runnable() {
//
//
//            @Override
//            public void run() {
//                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
//                imageView.setAnimation(animation);
//
//
//            }
//        }, 11000);







        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // For Orientation restricted to portrait
//        wb=(WebView)findViewById(R.id.webview);
//        wb.setWebContentsDebuggingEnabled(false);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider1);
        sliderrelative=(LinearLayout)findViewById(R.id.sliderlayout);
        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        queue = Volley.newRequestQueue(this);


        linearlayoutmain=(LinearLayout)findViewById(R.id.gridlayout);


        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

        gridView = (GridView) findViewById(R.id.gridView);
        GridAdapter gadapter = new GridAdapter(this, lettersIcon, letterList);
        gridView.setAdapter(gadapter);
        gridView.setOnItemClickListener(this);

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
        progressDialog = new ProgressDialog(this);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("connecting to MAHE Utsav server");
        progressDialog.setTitle(" Retrieving server...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        mDetector = new GestureDetector(this, new Home_screen.MyGestureListener());


//        if (fragment == null) {
//            fragment = new HorizontalListViewFragment();
//            ;
//            fm.beginTransaction()
//                    .add(R.id.fragmentContainer, fragment)
//                    .commit();
//        }
        HashMap<String, Integer> url_maps = new HashMap<>();


        url_maps.put("MAHE Utsav 2017 - Cultural program", R.drawable.slide7);
        url_maps.put("MAHE Utsav 2017 - Butterfly Dance", R.drawable.slide4);


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
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
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

//                        loadimages();
                        progressDialog.setMessage("Please wait...");
                        progressDialog.setTitle("Loading Images");

                    }

                }
            };
            Handler pdCanceller3 = new Handler();
            pdCanceller3.postDelayed(progressRunnable3, 3000);


            Runnable progressRunnable4 = new Runnable() {

                @Override
                public void run() {
                    {

                        //  loadimages();


                        if(!version_name.equals(latestVersion))
                        {
                            update_available_popup();
                        }
                        progressDialog.dismiss();

                        //     Toast.makeText(MainActivity.this, "Failed to connect .. please try again after sometime", Toast.LENGTH_SHORT).show();





                    }

                }
            };
            Handler pdCanceller4 = new Handler();
            pdCanceller4.postDelayed(progressRunnable4, 7000);




//            get_news();

        } else {
//            Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_LONG).show();
//            progressDialog.dismiss();


//            progressDialog1.cancel();
//            progressDialog1.dismiss();
        }










    }
  void setMarqueeSpeed(TextView tv, float speed) {
        if (tv != null) {
            try {
                Field f = null;
                if (tv instanceof AppCompatTextView) {
                    f = tv.getClass().getSuperclass().getDeclaredField("mMarquee");
                } else {
                    f = tv.getClass().getDeclaredField("mMarquee");
                }
                if (f != null) {
                    f.setAccessible(true);
                    Object marquee = f.get(tv);
                    if (marquee != null) {
                        String scrollSpeedFieldName = "mScrollUnit";
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            scrollSpeedFieldName = "mPixelsPerSecond";
                        }
                        Field mf = marquee.getClass().getDeclaredField(scrollSpeedFieldName);
                        mf.setAccessible(true);
                        mf.setFloat(marquee, speed);
                    }
                } else {
                    Toast.makeText(Home_screen.this, "", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    public void setTickerAnimation(View view) {
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, +1f,
                Animation.RELATIVE_TO_SELF, -1f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.RESTART);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(10000);
        view.startAnimation(animation);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home) {
            item.setChecked(true);

            // Handle the camera action
        } else if (id == R.id.nav_exit) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Home_screen.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();

        } else if (id == R.id.nav_help_desk) {
//            Toast.makeText(this,"This feature is'nt available right now,stay tuned for full version", Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(this,CCClist.class);
            startActivity(intent);


        }  else if (id == R.id.nav_share) {

            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

            // Add data to the intent, the receiving app will decide
            // what to do with it.
            String head="";
            share.putExtra(Intent.EXTRA_SUBJECT, head);
            share.putExtra(Intent.EXTRA_TEXT, "Click the link below to Download *MAHE Utsav Android App* Android app from Google playstore \nhttps://play.google.com/store/apps/details?id=com.creativecodes.maheutsav2019");
            startActivity(Intent.createChooser(share, "Share details via!"));



        } else if (id == R.id.about) {

            Intent intent=new Intent(this,ContactUs.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
            Intent intent=new Intent(this,Event_list.class);
            intent.putExtra("type","events");
            startActivity(intent);
        }


        if (textView.getText().toString().equals("General Rules")){
            Toast.makeText(this,"General Rules of  MAHE Utsav 2019", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(Home_screen.this,Rules.class);
            startActivity(intent);
        }
        if (textView.getText().toString().equals("Instagram")){
//
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://www.instagram.com/utsavmanipal/"));
                            intent.setPackage("com.instagram.android");
                            startActivity(intent);
                        }
                        catch (android.content.ActivityNotFoundException anfe)
                        {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.instagram.com/" + "utsavmanipal")));
                        }
        }
        if (textView.getText().toString().equals("Facebook")){



            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network

                Intent intent=new Intent(Home_screen.this,web_view.class);
                Toast.makeText(this, "Opening Mahe Utsav facebook page", Toast.LENGTH_SHORT).show();
                intent.putExtra("url","https://www.facebook.com/mahe.utsav/");
                startActivity(intent);

            } else {
                isNetworkConnectionAvailable();

//            startActivity(intent);
            }





        }

        if (textView.getText().toString().equals("Utsav Schedule")){



            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network

                Intent intent=new Intent(Home_screen.this,web_view.class);
                Toast.makeText(this, "Opening Mahe Utsav complete schedule", Toast.LENGTH_SHORT).show();
                intent.putExtra("url",utility.ip+"images/schedule.png");
                startActivity(intent);

            } else {
                isNetworkConnectionAvailable();

//            startActivity(intent);
            }





        }


        if (textView.getText().toString().equals("Notifications")){
//            Toast.makeText(this,"Notifications", Toast.LENGTH_SHORT).show();
//            Toast.makeText(this,"This feature is'nt available right now,stay tuned for full version", Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(Home_screen.this, Notifications.class);
            startActivity(intent);
        }
        if (textView.getText().toString().equals("Results")){
//            Toast.makeText(this,"Notifications", Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"This feature isn't available right now,stay tuned for further updates ", Toast.LENGTH_SHORT).show();


//            Intent intent=new Intent(Home_screen.this, Results.class);
//            intent.putExtra("round","5c9203e7b3a4a3131dcd0c17");
//            intent.putExtra("event","5c8b79b4c08d4f611524e193");
//
//            startActivity(intent);
        }
        if (textView.getText().toString().equals("Venue Map")){
            Toast.makeText(this,"Opening maps ", Toast.LENGTH_SHORT).show();
//
            Intent intent=new Intent(Home_screen.this, MapsActivity.class);

            startActivity(intent);
        }

    }




    public void checkNetworkConnection() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
//                finish();
            }
        });
        android.support.v7.app.AlertDialog alertDialog = builder.create();
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

    public void update_available_popup(){
        android.support.v7.app.AlertDialog.Builder builder =new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("Update Available");
        builder.setMessage("Please update the Manipal Utsav app to latest version on google play store");
        builder.setCancelable(false);


        builder.setPositiveButton("update now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent browserX = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.creativecodes.maheutsav2019"));
                startActivity(browserX);
                finish();

            }
        });



        android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
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

                url_maps_ads.clear();
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
                                TextSliderView textSliderView = new TextSliderView(Home_screen.this);
                                // initialize a SliderLayout

                                textSliderView
                                        .description(name)
                                        .image(url_maps_ads.get(name))
                                        .setScaleType(BaseSliderView.ScaleType.Fit)
                                        .setOnSliderClickListener(Home_screen.this);

                                //add your extra information
                                textSliderView.bundle(new Bundle());
                                textSliderView.getBundle()
                                        .putString("extra", name);

                                mDemoSlider.addSlider(textSliderView);
//                                mDemoSlider.setVisibility(View.VISIBLE);

                                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Stack);
                                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
                                mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                                mDemoSlider.setDuration(3500);
                                mDemoSlider.addOnPageChangeListener(Home_screen.this);




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




    public class VersionCheckerapp extends AsyncTask<String, String, String> {

        String newVersion;

        @Override
        protected String doInBackground(String... params) {

            try {
                newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=com.creativecodes.maheutsav2019")
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
class GridAdapter extends BaseAdapter {

    private int icons[];
    private Context context;
    private String letters[];
    private LayoutInflater inflat;

    public GridAdapter(Context context,int icons[],String letters[]){
        this.context=context;
        this.icons=icons;
        this.letters=letters;


    }
    @Override
    public int getCount() {
        return letters.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView = convertView;
        if(convertView == null)
        {
            inflat=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflat.inflate(R.layout.custom_grid,null);


        }


        ImageView icon = (ImageView) gridView.findViewById(R.id.icons);
        TextView letter = (TextView) gridView.findViewById(R.id.letters);
//        if(position==0)
//        {
//            letter.setTextColor(Color.parseColor("#D7EA3838"));
//
//        }
        icon.setImageResource(icons[position]);
        letter.setText(letters[position]);
        return gridView;
    }


}