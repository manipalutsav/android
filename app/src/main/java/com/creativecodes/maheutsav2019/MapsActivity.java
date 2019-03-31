package com.creativecodes.maheutsav2019;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    EditText from, to;
    Button getdirection;
    ArrayList<LatLng> markerPoints;
    utility utility;
    Button showbus;
    RequestQueue queue;
    StringRequest postRequest;
    String lat, lon;
    JsonObjectRequest jsonObjectRequest;
    String[] id;
    AutoCompleteTextView sourcetext;
    AutoCompleteTextView destinationtext;
    String[] languages;
    String fromlat, fromlon, tolat, tolon;
    String destinationname, bustype;
    TextView textroute;
    ListView busname;
    String src, des;
    ArrayList<LatLng> locList = new ArrayList<LatLng>();
    String one, two, three, four,bus_id;
    RecyclerView recyclerview;
    RecyclerView.Adapter adapter;
    private GoogleMap mMap;
    LatLng origin;
    LatLng dest;
    String place;
    ArrayList<LatLng> MarkerPoints;
    TextView ShowDistanceDuration;
    Polyline line;
    ProgressDialog progressDialog;
    ProgressDialog progressDialog2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_direction);
        queue = Volley.newRequestQueue(getApplicationContext());
//        src=getIntent().getStringExtra("src");
//        des=getIntent().getStringExtra("des");
//        bus_id=getIntent().getStringExtra("bus_id");
        utility = new utility();
//        getdirection=(Button)findViewById(R.id.buttongetdirection) ;
        // Initializing
        markerPoints = new ArrayList<LatLng>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }




        ConnectivityManager connectivityManager1 = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager1.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager1.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a networ


            progressDialog2 = new ProgressDialog(MapsActivity.this);
            progressDialog2.setMessage("Loading Maps");
            progressDialog2.setTitle("please wait...");
            progressDialog2.setCancelable(false);
            progressDialog2.show();



//            get_map_direction("13.353412","74.787797","TMA pai Hall");

            Runnable progressRunnable = new Runnable() {

                @Override
                public void run() {
                    if(progressDialog2.isShowing())
                    {
                        progressDialog2.cancel();
//                    finish();
                    }


                }
            };

            Handler pdCanceller = new Handler();
            pdCanceller.postDelayed(progressRunnable, 2000);
        } else {

            isNetworkConnectionAvailable();
        }


//        String v1 = "12.8648";
//        String  v2= "74.8584";
//        Double lat = Double.parseDouble(v1);
//        Double lan = Double.parseDouble(v2);
//
//         LatLng frompoint = new LatLng(lat,lan);


//
//        getdirection.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//
//                src=sourcetext.getText().toString();
//                des=destinationtext.getText().toString();
////                Toast.makeText(Map_direction.this,src, Toast.LENGTH_LONG).show();
//                if (src.equals(des)) {
//                    Toast.makeText(MapsActivity.this, "Source and destination cannot be same", Toast.LENGTH_SHORT).show();
//                } else {
////                    get_lat();
//
//                }
//
//
//            }
//        });


        // Initializing
        MarkerPoints = new ArrayList<>();

        //show error dialog if Google Play Services not available
        if (!isGooglePlayServicesAvailable()) {
            Log.d("onCreate", "Google Play Services not available. Ending Test case.");
            finish();
        } else {
            Log.d("onCreate", "Google Play Services available. Continuing.");
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map3);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.isMyLocationEnabled();
        mMap.setBuildingsEnabled(false);
        // Add a marker in Sydney and move the camera
        LatLng Model_Town = new LatLng(13.353185, 74.786640);
//        mMap.addMarker(new MarkerOptions().position(Model_Town).title("Marker in karkal"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Model_Town));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));

        LatLng myPosition;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);

    drawmap("13.353412","74.787797","Dr.TMA Pai Hall");
        drawmap("13.353306","74.784875","Counseling Hall,Manipal.edu ");
        drawmap("13.353185","74.786640","KMC Greens");
        drawmap("13.356315","74.785046","MMMC,Manipal");



        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude);
            myPosition = new LatLng(latitude, longitude);


            LatLng coordinate = new LatLng(latitude, longitude);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 12);
            mMap.animateCamera(yourLocation);
        }











        // Setting onclick event listener for the map
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//
//            @Override
//            public void onMapClick(LatLng point) {
//
//                // clearing map and generating new marker points if user clicks on map more than two times
//                if (MarkerPoints.size() > 1) {
//                    mMap.clear();
//                    MarkerPoints.clear();
//                    MarkerPoints = new ArrayList<>();
//                    ShowDistanceDuration.setText("");
//                }
//
//                // Adding new item to the ArrayList
//                MarkerPoints.add(point);
//
//                // Creating MarkerOptions
//                MarkerOptions options = new MarkerOptions();
//
//                // Setting the position of the marker
//                options.position(point);
//
//                /**
//                 * For the start location, the color of marker is GREEN and
//                 * for the end location, the color of marker is RED.
//                 */
//                if (MarkerPoints.size() == 1) {
//                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//                } else if (MarkerPoints.size() == 2) {
//                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                }
//
//
//                // Add new marker to the Google Map Android API V2
//                mMap.addMarker(options);
//
//                // Checks, whether start and end locations are captured
//                if (MarkerPoints.size() >= 2) {
//                    origin = MarkerPoints.get(0);
//                    dest = MarkerPoints.get(1);
//                }
//
//            }
//        });


    }



    // Checking if Google Play Services Available or not
    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

















    public boolean isNetworkConnectionAvailable(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if(isConnected) {
            Log.d("Network", "Connected");
            return true;
        }
        else{
            checkNetworkConnection();
            Log.d("Network","Not Connected");
            return false;
        }
    }
    public void checkNetworkConnection(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }









//    public void autosearch() {
//
////         jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, utility.ip+"get_data.php",
////                new Response.Listener<JSONObject>()
//
//        postRequest = new StringRequest(Request.Method.POST, utility.ip+"get_stops.php",
//                new com.android.volley.Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog2.dismiss();
////                       Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
//
//                        try {
//                            // Create the root JSONObject from the JSON string.
//                            JSONArray jsonArray = new JSONArray(response);
//                            languages=new String[jsonArray.length()];
//
//                            for(int i=0; i < jsonArray.length(); i++){
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                id = jsonObject.getString("stop_name").split(",");
//
////                                Toast.makeText(getActivity(), id[0].toString() + "\n", Toast.LENGTH_SHORT).show();
//                                languages[i]=id[0].toString();
//
//                            }
//                            ArrayAdapter adapter = new
//                                    ArrayAdapter(MapsActivity.this,android.R.layout.simple_list_item_1,languages);
//
//                            sourcetext.setAdapter(adapter);
//                            sourcetext.setThreshold(1);
//
//                            destinationtext.setAdapter(adapter);
//                            destinationtext.setThreshold(1);
//
//                        } catch (JSONException e) {e.printStackTrace();}
//                    }
//
//
//                },
//                new com.android.volley.Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d("Error.Response", error.toString());
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams()
//            {
//                Map<String, String>  params = new HashMap<String, String>();
////                params.put("src", sourcename);
////                params.put("dest",destinationname);
//
//                return params;
//            }
//        };
//        queue.add(postRequest);
//    }
//







    public void checkmap(){
        AlertDialog.Builder builder =new AlertDialog.Builder(MapsActivity.this);
        builder.setTitle("INVALID LOCATION");
        builder.setMessage("location not found");
        builder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    void drawmap(String latt,String lng,String loc)
    {




        String f1 = latt;
        String f2 = lng;
        Double flat = Double.parseDouble(f1);
        Double flan = Double.parseDouble(f2);

        origin = new LatLng(flat, flan);

        locList.add(origin);
        MarkerOptions options = new MarkerOptions()
                .position(origin)
                .draggable(false)
                .flat(false)
                .icon(BitmapDescriptorFactory.fromBitmap(createStoreMarker(loc)));
        Marker tempMarker = mMap.addMarker(options);
    }


    private Bitmap createStoreMarker(String loc) {
        View markerLayout = getLayoutInflater().inflate(R.layout.store_marker_layout, null);

        ImageView markerImage = (ImageView) markerLayout.findViewById(R.id.marker_image);
        TextView markerRating = (TextView) markerLayout.findViewById(R.id.marker_text);
        markerImage.setImageResource(R.drawable.utsav_logo_small);
        markerRating.setText(loc);

        markerLayout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        markerLayout.layout(0, 0, markerLayout.getMeasuredWidth(), markerLayout.getMeasuredHeight());

        final Bitmap bitmap = Bitmap.createBitmap(markerLayout.getMeasuredWidth(), markerLayout.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        markerLayout.draw(canvas);
        return bitmap;
    }

}
