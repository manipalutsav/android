package com.creativecodes.maheutsav2019;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class web_view extends AppCompatActivity {
    WebView wb;

    ProgressDialog progressDialog;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        progressDialog = new ProgressDialog(this);
        url= getIntent().getStringExtra("url");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Loading");
        progressDialog.setTitle(" Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        wb=(WebView)findViewById(R.id.webview2);
        wb.setWebContentsDebuggingEnabled(false);
        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings() .setLoadWithOverviewMode(true);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setBuiltInZoomControls(true);
        wb.getSettings().setPluginState(WebSettings.PluginState.ON);
//        wb.getSettings().setPluginsEnabled(true);
        wb.setWebViewClient(new web_view.tourismHelloWebViewClient());
        wb.loadUrl(url);

        wb.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {

//                Toast.makeText(web_view.this, "Completed", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                // do your stuff here
            }
        });


    }

    private class tourismHelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }
}
